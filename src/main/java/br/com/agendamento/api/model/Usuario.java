package br.com.agendamento.api.model;

import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
@Table
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String nome;
    private String email;
    private String senha;
    private Long id_status;


    public Usuario(UsuarioCadastroDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.id_status = dados.id_status();
    }


}
