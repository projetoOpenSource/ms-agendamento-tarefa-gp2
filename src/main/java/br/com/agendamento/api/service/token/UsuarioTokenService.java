package br.com.agendamento.api.service.token;

import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.exception.UsuarioNaoEncontradoException;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.repository.UsuarioTokenRepository;
import br.com.agendamento.api.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.agendamento.api.service.email.EmailService.envioEmailComTokenNoCorpo;

/**
 * Service email
 *
 * @author Edson Rafael
 */
@Slf4j
@Service
public class UsuarioTokenService {


    @Autowired
    private UsuarioTokenRepository tokenRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmailService emailService;

    /**
     * Metodo para confirma email com token
     *
     * @author Edson Rafael
     */
    @Transactional
    public void confirmarEmailComToken(String email, String condigoConfirmacao) {
        try {
            var user = Optional.ofNullable(usuarioRepository.findByEmail(email))
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

            var emailToken = tokenRepository.findByCodigoConfirmacao(condigoConfirmacao);

            if (!user.getIdUsuario().equals(emailToken.getIdUsuario())) {
                throw new ValidacaoException("Email incorreto do cadastro");
            }

            if (emailToken.getDataExpiracao().isBefore(LocalDateTime.now())) {
                var email1 = envioEmailComTokenNoCorpo(user.getEmail(), user.getIdUsuario());
                emailService.enviaEmailComToken(email1);
                log.info("Token expirado enviado novamente");
            } else if (emailToken.getCodigoConfirmacao().equals(condigoConfirmacao)) {
                user.setIdStatus(new Status(2L));
                emailService.enviarEmailComAgradecimento(user.getEmail());
            }

        } catch (DataAccessException ex) {
            throw new InternalErrorException(ex.getMessage());
        } catch (MailException e) {
            throw new InternalErrorException("Erro ao enviar o e-mail");
        } catch (InternalErrorException e) {
            throw new InternalErrorException("Ocorreu um error interno");
        }
    }


}
