package com.example.ogestor.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CriptografiaUtil {

    private static final String ALGORITMO = "AES";

    public static String criptografar(String texto) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        SecretKeySpec key = new SecretKeySpec(Config.getChaveCripto().getBytes(), ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String descriptografar(String textoCriptografado) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        SecretKeySpec key = new SecretKeySpec(Config.getChaveCripto().getBytes(), ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(textoCriptografado));
        return new String(decrypted);
    }
}
