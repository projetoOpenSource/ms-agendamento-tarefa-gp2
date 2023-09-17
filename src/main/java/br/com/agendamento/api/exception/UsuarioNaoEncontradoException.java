package br.com.agendamento.api.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }
}
