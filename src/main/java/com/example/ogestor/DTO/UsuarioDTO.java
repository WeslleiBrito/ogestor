package com.example.ogestor.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class UsuarioDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private final String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private final String email;

    @NotBlank(message = "Senha não pode ser vazia")
    private final String senha;

    // Construtor
    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    // Validação de instância
    public void validar() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<jakarta.validation.ConstraintViolation<UsuarioDTO>> violations = validator.validate(this);

            if (!violations.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (var v : violations) {
                    sb.append(v.getMessage()).append("\n");
                }
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }
}
