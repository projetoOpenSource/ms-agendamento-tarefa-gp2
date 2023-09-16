package br.com.agendamento.api.service.usuario;


import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static br.com.agendamento.api.service.email.EmailService.envioEmailComTokenNoCorpo;

/**
 * Service usuario
 *
 * @author Edson Rafael
 */
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository reposiroty;
    @Autowired
    private EmailService emailService;


    /**
     * Metodo para cadastrada usuario
     *
     * @author Edson Rafael
     */
    @Transactional
    public void cadastro(UsuarioCadastroDTO dados) throws ValidacaoException, InternalErrorException {
        try {
            if (!Objects.equals(dados.senha(), dados.senhaConfirma())) {
                throw new ValidacaoException("Senha não coincidem");
            }

            if (reposiroty.existsByEmail(dados.email())) {
                throw new ValidacaoException("Email já cadastrado");
            }

            var usuario = new Usuario(null, dados.nome(), dados.email().toLowerCase(), dados.senha(), new Status(1L));
            reposiroty.save(usuario);


            var email = envioEmailComTokenNoCorpo(dados.email(), usuario.getIdUsuario());
            emailService.enviaEmailComToken(email);

        } catch (DataAccessException ex) {
            throw new InternalErrorException(ex.getMessage());
        }
    }

}
