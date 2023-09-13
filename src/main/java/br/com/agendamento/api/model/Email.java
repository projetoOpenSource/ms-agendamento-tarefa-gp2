package br.com.agendamento.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_token")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_token")
    private Long idUsuarioToken;

    @Column(name = "codigo_confirmacao")
    private String codigoConfirmacao;

    @Column(name = "data_expiracao")
    private LocalDateTime dataExpiracao;

    @Column(name = "id_usuario")
    private Long idUsuario;
    @Transient
    private String emailTo;
    @Transient
    private String msg;
    @Transient
    private String emailFrom;
    @Transient
    private String subject;


    public Email(String emailFrom, String emailTo, String codigoConfirmacao,String msg ,String subject, Long idUsuarioToken) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.codigoConfirmacao = codigoConfirmacao;
        this.subject = subject;
        this.idUsuario = idUsuarioToken;
        this.msg = msg;
    }

    public Email(String emailTo){
        this.emailFrom = emailFrom;
    }

}