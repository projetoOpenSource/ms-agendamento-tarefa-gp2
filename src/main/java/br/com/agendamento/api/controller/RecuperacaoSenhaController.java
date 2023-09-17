package br.com.agendamento.api.controller;


import br.com.agendamento.api.dto.usuario.UsuarioAtualizarSenhaDTO;
import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.service.recuperaSenha.RecuperaSenhaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller email
 *
 * @author Edson Rafael
 */
@Api(value = "RecuperacaoSenha", tags = {"RecuperacaoSenha"})
@RestController
@RequestMapping("/recuperacao-senha")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecuperacaoSenhaController {

    @Autowired
    private RecuperaSenhaService senhaService;

    /**
     * Endpoint para enviar email com token para recuperacao de senha
     *
     * @return ok
     */
    @PostMapping("/{email}")
    public ResponseEntity<UsuarioToken> enviarEmailComTokenParaRecuperacaoSenha(@PathVariable String email) {
        senhaService.recuperarSenha(email);

        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para atualizar senha com token e novo senha.
     *
     * @return ok
     */
    @PostMapping("/{email}/{token}")
    public ResponseEntity<UsuarioToken> updateSenha(@PathVariable String email,
                                                    @PathVariable String token,
                                                    @RequestBody @Valid UsuarioAtualizarSenhaDTO dados) {
        senhaService.atualizarSenha(email, token, dados);

        return ResponseEntity.ok().build();
    }

}
