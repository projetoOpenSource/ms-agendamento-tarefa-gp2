package br.com.agendamento.api.service.email;

import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.exception.UsuarioNaoEncontradoException;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.repository.UsuarioTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.agendamento.api.util.TokenGenerator.enviarEmailComCod;

/**
 * Service email
 *
 * @author Edson Rafael
 */
@Service
public class UsuarioTokenService {


    @Autowired
    private UsuarioTokenRepository tokenRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    /**
     * Metodo para envio com token
     *
     * @author Edson Rafael
     */
    @Transactional
    public void sendEmail(UsuarioToken usuarioTokenModel) throws InternalErrorException {
        usuarioTokenModel.setDataExpiracao(LocalDateTime.now().plusHours(24));
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(usuarioTokenModel.getEmailTo());
            message.setSubject(usuarioTokenModel.getSubject());
            message.setText(usuarioTokenModel.getCodigoConfirmacao());
            message.setText(usuarioTokenModel.getMsg());
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new InternalErrorException("Ocorreu um erro ao enviar o email");
        }
        tokenRepository.save(usuarioTokenModel);
    }

    /**
     * Metodo para validação e confirmação do token
     *
     * @author Edson Rafael
     */
    @Transactional
    public void confirmaEmail(String email, String condigoConfirmacao) {
        try {
            var user = Optional.ofNullable(usuarioRepository.findByEmail(email))
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
            var emailToken = tokenRepository.findByCodigoConfirmacao(condigoConfirmacao);

            if (!user.getIdUsuario().equals(emailToken.getIdUsuario())){
                throw new ValidacaoException("Email incorreto do cadastro");
            }

            if (emailToken.getDataExpiracao().isBefore(LocalDateTime.now())) {
                var email1 = enviarEmailComCod(user.getEmail(), user.getIdUsuario());
                sendEmail(email1);
                throw new InternalErrorException("Token expirado enviando novo");
            } else if (emailToken.getCodigoConfirmacao().equals(condigoConfirmacao)) {
                user.setIdStatus(new Status(2L));
                sendEmailConfirm(user.getEmail());
            }

        } catch (DataAccessException ex) {
            throw new InternalErrorException(ex.getMessage());
        } catch (MailException e) {
            throw new InternalErrorException("Erro ao enviar o e-mail");
        } catch (InternalErrorException e) {
            throw new InternalErrorException("Ocorreu um error interno");
        }
    }


    /**
     * Metodo para envio email com agradecimento ao usuario
     *
     * @author Edson Rafael
     */
    @Transactional
    public void sendEmailConfirm(String email) throws InternalErrorException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(email);
            message.setSubject("Email Confirmado");
            message.setText("Obrigado por confirmar o email");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new InternalErrorException("Ocorreu um erro ao enviar o email");
        }
    }


}
