package br.com.agendamento.api.controller;


import br.com.agendamento.api.model.Email;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.service.email.EmailService;
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
public class EmailController {

    @Autowired
    private EmailService service;

    @GetMapping("/{codigo}/{idUsuario}")
    public ResponseEntity<Email> update(@PathVariable String codigo, @PathVariable Long idUsuario) {
        service.confirmaEmail(codigo, new Usuario(idUsuario));
        return ResponseEntity.noContent().build();
    }


}
