package br.com.agendamento.api.service.usuario;

import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.model.Email;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.EmailRepository;
import br.com.agendamento.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.agendamento.api.util.TokenGenerator.getEmail;

/**
 * Service email
 *
 * @author Edson Rafael
 */
@Service
public class EmailService {


    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * Metodo para envio com token
     *
     * @author Edson Rafael
     */
    @Transactional
    public void sendEmail(Email emailModel) throws InternalErrorException {
        emailModel.setDataExpiracao(LocalDateTime.now().plusHours(24));
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getCodigoConfirmacao());
            message.setText(emailModel.getMsg());
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new InternalErrorException("Ocorreu um erro ao enviar o email");
        }
        emailRepository.save(emailModel);
    }

    /**
     * Metodo para validação e confirmação do token
     *
     * @author Edson Rafael
     */
    @Transactional
    public void confirmaEmail(String codigo, Usuario usuario) {
        Email emailModel = emailRepository.findByCodigoConfirmacao(codigo);
        var user = usuarioRepository.getReferenceById(usuario.getIdUsuario());

        if (emailModel != null && emailModel.getDataExpiracao() != null) {
            if (emailModel.getDataExpiracao().isBefore(LocalDateTime.now())) {
                var email = getEmail(user.getEmail(), usuario.getIdUsuario());
                sendEmail(email);
            }
            user.setIdStatus(new Status(2L));
            sendEmailConfirm(user.getEmail());
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
            message.setFrom("batista756l@gmail.com");
            message.setTo(email);
            message.setSubject("Email Confirmado");
            message.setText("Obrigado por confirmar o email");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new InternalErrorException("Ocorreu um erro ao enviar o email");
        }
    }


}
