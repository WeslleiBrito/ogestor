package com.example.ogestor.util;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getChaveCripto() {
        return dotenv.get("CHAVE_CRIPTO");
    }
}
