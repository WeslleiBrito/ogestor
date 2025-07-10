package com.example.ogestor.exception;

public class ErrorDefault {
    private final String mensagemUsuario;
    private final String mensagemDesenvolvedor;

    public ErrorDefault(String mensagemUsuario, String mensagemDesenvolvedor) {
        this.mensagemUsuario = mensagemUsuario;
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    // Getters
    public String getMensagemUsuario() {
        return mensagemUsuario;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }

    @Override
    public String toString() {
        return String.format("Erro: %s | Detalhes técnicos: %s", mensagemUsuario, mensagemDesenvolvedor);
    }
}
