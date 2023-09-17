package br.com.agendamento.api.util;

import java.util.UUID;


/**
 * Metodos para gerar token e enviar
 *
 * @author Edson Rafael
 */

public class TokenGenerator {
    public static String generateUniqueToken() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
