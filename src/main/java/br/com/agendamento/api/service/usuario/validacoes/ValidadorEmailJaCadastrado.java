package br.com.agendamento.api.service.usuario.validacoes;

import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.repository.UsuarioReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEmailJaCadastrado implements ValidadorCadastroUsuario {
    @Autowired
    private UsuarioReposiroty reposiroty;

    @Override
    public void validar(UsuarioCadastroDTO dados) {
        if (reposiroty.findByEmail(dados.email()) != null) {
            throw new ValidacaoException("Email já cadastrado");
        }
    }


}
