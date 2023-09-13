package br.com.agendamento.api.util;

import br.com.agendamento.api.model.Email;
import br.com.agendamento.api.model.Usuario;

import java.util.Random;

public class TokenGenerator {
    public static Email getEmail(String email, Usuario usuario) {
        String token = gerarCodigo();

        String msg = "\nSeja bem-vindo(a)" +
                     "\nConfirme sua conta com esse codigo: " + token +
                     "\n Link confirmação: http://localhost:8080/ms-agendamento-tarefa/" + token + "/" + usuario.getIdUsuario();
        return new Email("batista756l@gmail.com", email, token, msg, "Bem-Vindo", usuario.getIdUsuario());
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
