package br.com.agendamento.api.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UsuarioCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String senhaConfirma
) {
}
