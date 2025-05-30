package com.example.ogestor.controller;

import com.example.ogestor.componentes.Login;
import com.example.ogestor.componentes.Signup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    private Login loginJanela;

    public void setLoginJanela(Login loginJanela) {
        this.loginJanela = loginJanela;
    }

    @FXML
    private void handleCallSignup(ActionEvent event) {
        // abrir nova tela
        new Signup().show();

        // fechar esta
        loginJanela.close();
    }

    @FXML
    private void onEntrarClick(ActionEvent event) {
        // lógica de login

    }

    @FXML
    private void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "1234".equals(password)) {
            messageLabel.setText("Login bem-sucedido!");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            // redirecionar para a próxima tela, se necessário
        } else {
            messageLabel.setText("Usuário ou senha incorretos.");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
