package br.com.agendamento.api.controller;

import br.com.agendamento.api.dto.usuario.UsuarioCadastroDTO;
import br.com.agendamento.api.dto.usuario.UsuarioResponseDTO;
import br.com.agendamento.api.service.usuario.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller usuario
 *
 * @author Edson Rafael
 */
@Api(value = "Usuario", tags = { "Usuario" })
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    /**
     * Endpoint que cadastra usuario
     *
     * @return UsuarioResponseDTO
     */
    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponseDTO> cadastro(@RequestBody @Valid UsuarioCadastroDTO dados) {
        service.cadastro(dados);
        var dto = new UsuarioResponseDTO(dados);
         return ResponseEntity.ok(dto);
    }

}
