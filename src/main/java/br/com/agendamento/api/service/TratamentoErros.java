package br.com.agendamento.api.service;

import br.com.agendamento.api.exception.ValidacaoException;
import br.com.agendamento.api.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErros {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErrorResponse> tratarErroRegraDeNegocio(ValidacaoException ex) {
        var errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
