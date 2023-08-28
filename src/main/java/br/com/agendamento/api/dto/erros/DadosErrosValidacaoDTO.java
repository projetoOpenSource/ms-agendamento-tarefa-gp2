package br.com.agendamento.api.dto.erros;

import org.springframework.validation.FieldError;

public record DadosErrosValidacaoDTO(String campo, String msg) {
    public DadosErrosValidacaoDTO(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
