package br.com.agendamento.api.service.usuario.validacoes;

import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;

public interface ValidadorCadastroUsuario {
    void validar(UsuarioCadastroDTO dados);
}
