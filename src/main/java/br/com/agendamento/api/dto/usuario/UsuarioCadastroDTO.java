package br.com.agendamento.api.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UsuarioCadastroDTO(
        @NotBlank
        @Size(max = 50, message = "Excedido limite de 50 caracteres")
        String nome,
        @NotBlank
        @Email
        @Size(max = 100, message = "Excedido limite de 100 caracteres")
        String email,
        @NotBlank
        @Size(max = 100, message = "Excedido limite de 100 caracteres")
        String senha,
        @NotBlank
        String senhaConfirma
) {
}
