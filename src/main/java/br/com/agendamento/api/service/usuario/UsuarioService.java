package br.com.agendamento.api.service.usuario;


import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository reposiroty;


    public void cadastro(UsuarioCadastroDTO dados) throws ValidacaoException, InternalErrorException {
        if (!Objects.equals(dados.senha(), dados.senhaConfirma())) {
            throw new ValidacaoException("Senha não coincidem");
        }

        if (reposiroty.findByEmail(dados.email()) != null) {
            throw new ValidacaoException("Email já cadastrado");
        }

        var usuario = new Usuario(null, dados.nome(), dados.email().toLowerCase(), dados.senha(), 1L);
        reposiroty.save(usuario);
        enviarEmailBoasVindas(dados.email());
    }

    private void enviarEmailBoasVindas(String emailDestinatario) {
        //Implementação
    }
}
