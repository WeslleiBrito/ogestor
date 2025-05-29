package com.example.ogestor.util;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaUtil {

    public static String hashSenha(String senhaPlana) {
        return BCrypt.hashpw(senhaPlana, BCrypt.gensalt());
    }

    public static boolean verificaSenha(String senhaPlana, String senhaHash) {
        return  BCrypt.checkpw(senhaPlana, senhaHash);
    }
}
