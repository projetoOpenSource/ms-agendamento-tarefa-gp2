package br.com.agendamento.api.model;

import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
@Table
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status idStatus;

    public Usuario(UsuarioCadastroDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


}
