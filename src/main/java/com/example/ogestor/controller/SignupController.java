package com.example.ogestor.controller;


import com.example.ogestor.DAO.UsuarioDAO;
import com.example.ogestor.DTO.UsuarioDTO;
import com.example.ogestor.componentes.Login;
import com.example.ogestor.componentes.Signup;
import com.example.ogestor.model.Usuario;
import com.example.ogestor.util.SenhaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SignupController {
    private Signup signupJanela;
    @FXML private TextField nomeTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField cpfTextField;
    @FXML private PasswordField senhaPasswordField;
    @FXML private PasswordField confirmaSenhaPasswordField;
    @FXML private Label avisoNome;
    @FXML private Label avisoEmail;
    @FXML private Label avisoCPF;
    @FXML private Label avisoSenha;
    @FXML private Label confirmarSenha;
    @FXML private Button voltarButton;
    @FXML private Button sair;


    @FXML
    public void handleRegister() {

        Alert alert = new Alert(null);
        boolean senhasIguais = true;

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                nomeTextField.getText(),
                emailTextField.getText(),
                senhaPasswordField.getText(),
                confirmaSenhaPasswordField.getText(),
                cpfTextField.getText()
        );

        Map<String, String> erros = usuarioDTO.validar();

        if(!erros.isEmpty()) {
            for (String campo : erros.keySet()){
                switch (campo) {
                    case "nome" -> {
                            avisoNome.setText(erros.get(campo));
                            avisoNome.setTextFill(javafx.scene.paint.Color.RED);
                    }
                    case "email" -> {
                            avisoEmail.setText(erros.get(campo));
                            avisoEmail.setTextFill(javafx.scene.paint.Color.RED);
                    }
                    case "senha" -> {
                            avisoSenha.setText(erros.get(campo));
                            avisoSenha.setTextFill(javafx.scene.paint.Color.RED);
                    }
                    case "confirmarSenha" -> {
                        confirmarSenha.setText(erros.get(campo));
                        confirmarSenha.setTextFill(javafx.scene.paint.Color.RED);
                    }
                    case "cpf" -> {
                        avisoCPF.setText(erros.get(campo));
                        avisoCPF.setTextFill(javafx.scene.paint.Color.RED);
                    }
                }
            }
        }

        if(!senhaPasswordField.getText().equals(confirmaSenhaPasswordField.getText())) {
            senhasIguais = false;
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("As senhas são diferentes.");
        }

        List<Usuario> usuarios = UsuarioDAO.buscarUsuarios();

        boolean cpfExiste = false;
        boolean emailExiste = false;

        for(Usuario usuario : usuarios){
            if (usuario.getEmail().equals(usuarioDTO.getEmail())){
                emailExiste = true;
                avisoEmail.setText("O email informado já existe.");
                avisoEmail.setTextFill(javafx.scene.paint.Color.RED);
            }

            if (usuario.getCpf().equals(usuarioDTO.getCpf())) {
                cpfExiste = true;
                avisoCPF.setText("O CPF informado já existe.");
                avisoCPF.setTextFill(javafx.scene.paint.Color.RED);
            }
        }

        if(erros.isEmpty() && senhasIguais && !emailExiste && !cpfExiste) {
            String id = UUID.randomUUID().toString();

            String senhaHash = SenhaUtil.hashSenha(senhaPasswordField.getText());

            Usuario usuario = new Usuario(
                    id,
                    usuarioDTO.getCpf(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getNome(),
                    senhaHash,
                    "56a60e1f-486e-41fb-bab6-ecb72acbbc71"
            );

            UsuarioDAO.salvarUsuario(usuario);

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setContentText("Cadastrado com sucesso!");
            alert.showAndWait();

            limparCaixas();
        }
    }

    @FXML
    private void removerStyloErro(javafx.scene.input.KeyEvent event) {
        Object source = event.getSource();
        if (source instanceof javafx.scene.control.TextInputControl campo) {
            campo.getStyleClass().remove("erro");
        }
    }

    @FXML
    private void handleBack (){
        new Login().show();
        signupJanela.close();
    }

    @FXML
    private void handleExitAction() {
        signupJanela.close();
    }

    public void setJanelaSignup (Signup signupJanela) {
        this.signupJanela = signupJanela;
    }

    private void limparCaixas() {
        nomeTextField.clear();
        emailTextField.clear();
        cpfTextField.clear();
        senhaPasswordField.clear();
        confirmaSenhaPasswordField.clear();
    }

}
