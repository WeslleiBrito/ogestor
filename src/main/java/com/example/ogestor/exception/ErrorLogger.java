package com.example.ogestor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorLogger {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLogger.class);

    public static ErrorDefault tratarErro(Exception e, String mensagemUsuario) {
        StackTraceElement elemento = e.getStackTrace()[0];

        String classe = elemento.getClassName();
        String metodo = elemento.getMethodName();
        int linha = elemento.getLineNumber();
        String mensagemDev = String.format("Erro em %s.%s (linha %d): %s", classe, metodo, linha, e.getMessage());

        logger.error(mensagemDev, e);  // log para desenvolvedor com stack trace completo

        return new ErrorDefault(mensagemUsuario, mensagemDev);
    }
}

