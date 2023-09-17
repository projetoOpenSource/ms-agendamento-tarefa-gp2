package br.com.agendamento.api.service.email;

import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.repository.UsuarioTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.agendamento.api.util.TokenGenerator.generateUniqueToken;

@Service
public class EmailService {


    @Autowired
    private UsuarioTokenRepository tokenRepository;
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
    public void enviaEmailComToken(UsuarioToken usuarioTokenModel) throws InternalErrorException {
        usuarioTokenModel.setDataExpiracao(LocalDateTime.now().plusHours(24));
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(usuarioTokenModel.getEmailTo());
            message.setSubject("Bem-Vindo");
            message.setText(usuarioTokenModel.getCodigoConfirmacao());
            message.setText(usuarioTokenModel.getMsg());
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new InternalErrorException("Ocorreu um erro ao enviar o email");
        }
        tokenRepository.save(usuarioTokenModel);
    }

    /**
     * Metodo para envio email com agradecimento ao usuario
     *
     * @author Edson Rafael
     */
    @Transactional
    public void enviarEmailComAgradecimento(String email) throws InternalErrorException {
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

    /**
     * Metodo para envio email com token apos cadastro ou token expirado
     *
     * @author Edson Rafael
     */
    public static UsuarioToken envioEmailComTokenNoCorpo(String email, Long id) {
        String token = generateUniqueToken();

        String msg = "\nSeja bem-vindo(a)" +
                     "\nConfirme sua conta com esse codigo: " + token +
                     "\n Link confirmação: http://localhost:8080/ms-agendamento-tarefa/" + email + "/" + token;
        return new UsuarioToken(email, token, msg, id);
    }

}
