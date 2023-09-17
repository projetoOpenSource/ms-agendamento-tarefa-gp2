package br.com.agendamento.api.controller;


import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.service.token.UsuarioTokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller email
 *
 * @author Edson Rafael
 */
@Api(value = "usuarioToken", tags = { "usuarioToken" })
@RestController
@RequestMapping("/confirmacao-email")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioTokenController {

    @Autowired
    private UsuarioTokenService service;

    /**
     * Endpoint que confirma o email do usuário.
     *
     * @return ok
     */
    @GetMapping("/{email}/{token}")
    public ResponseEntity<UsuarioToken> update(@PathVariable String email, @PathVariable String token) {
        service.confirmarEmailComToken(email, token);
        return ResponseEntity.ok().build();
    }

}
