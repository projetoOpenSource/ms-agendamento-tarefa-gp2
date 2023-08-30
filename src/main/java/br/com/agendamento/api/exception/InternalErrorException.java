package br.com.agendamento.api.exception;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String msg) {
        super(msg);
    }
}
