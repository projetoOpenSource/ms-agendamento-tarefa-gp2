package br.com.agendamento.api.dto.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UsuarioAtualizarSenhaDTO(
        @NotBlank
        @Size(max = 100, message = "Excedido limite de 100 caracteres")
        String senha,
        @NotBlank
        @Size(max = 100, message = "Excedido limite de 100 caracteres")
        String senhaConfirma
) {
}
