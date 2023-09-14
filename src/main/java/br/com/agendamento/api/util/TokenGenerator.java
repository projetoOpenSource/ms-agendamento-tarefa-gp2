package br.com.agendamento.api.util;

import br.com.agendamento.api.model.UsuarioToken;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * Metodos para gerar token e enviar
 *
 * @author Edson Rafael
 */

@Component
public class TokenGenerator {


    public static UsuarioToken enviarEmailComCod(String email, Long id) {
        String token = generateUniqueToken();

        String msg = "\nSeja bem-vindo(a)" +
                     "\nConfirme sua conta com esse codigo: " + token +
                     "\n Link confirmação: http://localhost:8080/ms-agendamento-tarefa/" + email + "/" + token;
        return new UsuarioToken(email, token, msg, "Bem-Vindo", id);
    }


    public static String generateUniqueToken() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }


}
