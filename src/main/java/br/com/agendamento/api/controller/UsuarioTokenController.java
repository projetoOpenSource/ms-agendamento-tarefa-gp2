package br.com.agendamento.api.controller;


import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.service.email.UsuarioTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller email
 *
 * @author Edson Rafael
 */
@RestController
public class UsuarioTokenController {

    @Autowired
    private UsuarioTokenService service;

    @GetMapping("/{email}/{codigoConfirmacao}")
    public ResponseEntity<UsuarioToken> update(@PathVariable String email, @PathVariable String codigoConfirmacao) {
        service.confirmaEmail(email, codigoConfirmacao);
        return ResponseEntity.noContent().build();
    }


}
