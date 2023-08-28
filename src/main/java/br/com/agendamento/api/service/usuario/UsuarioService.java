package br.com.agendamento.api.service.usuario;


import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.service.usuario.validacoes.ValidadorCadastroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository reposiroty;
    @Autowired
    private List<ValidadorCadastroUsuario> validadores;

    public void cadastro(UsuarioCadastroDTO dados) {
        validadores.forEach(v -> v.validar(dados));
        var usuario = new Usuario(dados);
        reposiroty.save(usuario);
        enviarEmailBoasVindas(dados.email());
    }

    private void enviarEmailBoasVindas(String emailDestinatario) {
        //Implementação
    }
}
