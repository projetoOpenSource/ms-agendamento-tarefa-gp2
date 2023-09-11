package br.com.agendamento.api.dto.usuario;

public record UsuarioResponseDTO(String nome, String email) {
    public UsuarioResponseDTO(UsuarioCadastroDTO usuario) {
        this(usuario.nome(), usuario.email().toLowerCase());
    }
}
