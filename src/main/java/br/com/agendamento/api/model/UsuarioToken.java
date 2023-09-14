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
public class UsuarioToken {

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
    private String subject;


    public UsuarioToken(String emailTo, String codigoConfirmacao, String msg , String subject, Long idUsuarioToken) {
        this.emailTo = emailTo;
        this.codigoConfirmacao = codigoConfirmacao;
        this.subject = subject;
        this.msg = msg;
        this.idUsuario = idUsuarioToken;
    }


}