package br.com.agendamento.api.util;

import br.com.agendamento.api.model.Email;

import java.util.Random;

/**
 * Metodos para gerar e enviar
 *
 * @author Edson Rafael
 */
public class TokenGenerator {
    public static Email getEmail(String email, Long usuarioId) {
        String token = gerarCodigo();

        String msg = "\nSeja bem-vindo(a)" +
                     "\nConfirme sua conta com esse codigo: " + token +
                     "\n Link confirmação: http://localhost:8080/ms-agendamento-tarefa/" + token + "/" + usuarioId;
        return new Email(email, token, msg, "Bem-Vindo", usuarioId);
    }


    public static String gerarCodigo() {
        String caracteres = "0123456789";

        Random random = new Random();

        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(index));
        }

        return codigo.toString();
    }

}
