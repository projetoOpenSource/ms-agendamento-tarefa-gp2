package br.com.agendamento.api.service.usuario.validacoes;

import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorConfirmacaoSenha implements ValidadorCadastroUsuario {

    @Override
    public void validar(UsuarioCadastroDTO dados) {
        if (!Objects.equals(dados.senha(), dados.senhaConfirma())) {
            throw new ValidacaoException("Senha não coincidem");
        }

    }
}
