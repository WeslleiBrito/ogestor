package com.example.ogestor.DTO;

import jakarta.validation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class UsuarioDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private final String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private final String email;

    @NotBlank(message = "Senha não pode ser vazia")
    private final String senha;

    @NotBlank(message = "Você deve confirmar a senha")
    private final String confirmarSenha;

    @NotBlank(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private final String cpf;

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public UsuarioDTO(String nome, String email, String senha, String confirmarSenha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.confirmarSenha = confirmarSenha;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getCpf()   { return cpf; }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public Map<String, String> validar() {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(this);
        Map<String, String> erros = new HashMap<>();

        for (ConstraintViolation<UsuarioDTO> v : violations) {
            erros.put(v.getPropertyPath().toString(), v.getMessage());
        }

        return erros;
    }
}
