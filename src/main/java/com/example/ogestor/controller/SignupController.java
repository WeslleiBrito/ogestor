package com.example.ogestor.controller;


import com.example.ogestor.DTO.UsuarioDTO;
import com.example.ogestor.componentes.Login;
import com.example.ogestor.componentes.Signup;
import com.example.ogestor.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignupController {
    private Signup signupJanela;
    @FXML private TextField nomeTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField senhaTextField;
    @FXML private TextField cpfTextField;
    @FXML private Button voltarButton;
    @FXML private Button sair;


    @FXML
    public void handleRegister() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                nomeTextField.getText(),
                emailTextField.getText(),
                senhaTextField.getText(),
                cpfTextField.getText()
        );

        try {
            usuarioDTO.validar();
            System.out.println("Usuário validado com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação");
            System.out.println(e.getMessage());
        }
    }


    public void setJanelaSignup (Signup signupJanela) {
        this.signupJanela = signupJanela;
    }
    @FXML
    private void handleBack (ActionEvent e){
        new Login().show();
        signupJanela.close();
    }

    @FXML
    private void handleExitAction(ActionEvent e) {
        signupJanela.close();
    }

    public void setSignupJanela(Signup signupJanela) {
        this.signupJanela = signupJanela;
    }
}
