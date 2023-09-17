package br.com.agendamento.api.dto.usuario;

import br.com.agendamento.api.model.Usuario;

public record UsuarioResponseDTO(String nome, String email) {
    public UsuarioResponseDTO(UsuarioCadastroDTO usuario) {
        this(usuario.nome(), usuario.email().toLowerCase());
    }

    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail().toLowerCase());
    }
}
