package com.example.ogestor.model;

public class Usuario {

    private final String id;
    private final String cpf;
    private String nome;
    private String senha;
    private String tipoUsuarioId;

    public Usuario(String id, String cpf, String nome, String senha, String tipoUsuarioId) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipoUsuarioId(String tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoUsuarioId='" + tipoUsuarioId + '\'' +
                '}';
    }
}
