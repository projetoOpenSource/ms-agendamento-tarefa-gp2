package br.com.agendamento.api.controller;


import br.com.agendamento.api.model.Email;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.service.usuario.EmailService;
import br.com.agendamento.api.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{codigo}/{idUsuario}")
    public ResponseEntity<Email> update(@PathVariable String codigo, @PathVariable Long idUsuario) {
        service.confirmaEmail(codigo, new Usuario(idUsuario));
        return ResponseEntity.noContent().build();
    }


}
