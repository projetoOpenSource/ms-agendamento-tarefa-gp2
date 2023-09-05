package br.com.agendamento.api.service.usuario;


import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.exception.InternalErrorException;
import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Service usuario
 *
 * @author Edson Rafael
 */
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository reposiroty;


    /**
     * Metodo para cadastrada usuario
     *
     * @author Edson Rafael
     */
    @Transactional
    public void cadastro(UsuarioCadastroDTO dados) throws ValidacaoException, InternalErrorException {
        try {
            if (!Objects.equals(dados.senha(), dados.senhaConfirma())) {
                throw new ValidacaoException("Senha não coincidem");
            }

            if (reposiroty.findByEmail(dados.email()) != null) {
                throw new ValidacaoException("Email já cadastrado");
            }

            var usuario = new Usuario(null, dados.nome(), dados.email().toLowerCase(), dados.senha(), new Status(1L));
            reposiroty.save(usuario);
            enviarEmailBoasVindas(dados.email());
        } catch (DataAccessException ex) {
            throw new InternalErrorException(ex.getMessage());
        }
    }

    private void enviarEmailBoasVindas(String emailDestinatario) {
        //Implementação
    }
}
