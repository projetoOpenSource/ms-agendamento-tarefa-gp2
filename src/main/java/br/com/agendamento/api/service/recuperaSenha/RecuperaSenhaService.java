package br.com.agendamento.api.service.recuperaSenha;

import br.com.agendamento.api.dto.usuario.UsuarioAtualizarSenhaDTO;
import br.com.agendamento.api.exception.UsuarioNaoEncontradoException;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.repository.UsuarioTokenRepository;
import br.com.agendamento.api.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static br.com.agendamento.api.service.email.EmailService.envioEmailComTokenParaRecuperacaoSenha;

@Service
public class RecuperaSenhaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioTokenRepository tokenRepository;


    /**
     * Metodo para enviar email com token para recuperacao de senha
     *
     * @author Edson Rafael
     */
    @Transactional
    public void recuperarSenha(String email) {
        var user = Optional.ofNullable(usuarioRepository.findByEmail(email))
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        var emailConteudo = envioEmailComTokenParaRecuperacaoSenha(user.getEmail(), user.getIdUsuario());
        emailService.enviaEmailComTokenParaRecuperacaoSenha(emailConteudo);

    }

    /**
     * Metodo para atualizar senha de usuario apos confirmacao do token
     *
     * @author Edson Rafael
     */
    @Transactional
    public void atualizarSenha(String email, String token, @Valid UsuarioAtualizarSenhaDTO dados) {
        var user = Optional.ofNullable(usuarioRepository.findByEmail(email))
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        var emailToken = Optional.ofNullable(tokenRepository.findByCodigoConfirmacao(token))
                .orElseThrow(() -> new ValidacaoException("Token invalido"));

        if (!Objects.equals(dados.senha(), dados.senhaConfirma())) {
            throw new ValidacaoException("Senhas não coincidem");
        }
        if (emailToken.getDataExpiracao().isBefore(LocalDateTime.now())) {
            throw new ValidacaoException("Token expirado");
        }
        if (!Objects.equals(emailToken.getIdUsuario(), user.getIdUsuario())) {
            throw new ValidacaoException("Email ou token invalido");
        }

        user.setSenha(dados.senha());
        usuarioRepository.save(user);
    }
}
