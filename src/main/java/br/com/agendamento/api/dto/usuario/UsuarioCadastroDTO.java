package br.com.agendamento.api.dto.usuario;

import javax.validation.constraints.*;

public record UsuarioCadastroDTO(
        @NotBlank
        @Size(max = 50, message = "Excedido limite de 50 caracteres")
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String senhaConfirma,

        Long id_status
) {
}
