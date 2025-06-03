package com.example.ogestor.controller;

import com.example.ogestor.DAO.UsuarioDAO;
import com.example.ogestor.componentes.Login;
import com.example.ogestor.componentes.Signup;
import com.example.ogestor.model.Usuario;
import com.example.ogestor.util.SenhaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;


public class LoginController {

    @FXML private ComboBox<Usuario> usuarioComboBox;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    private Login loginJanela;

    public void setLoginJanela(Login loginJanela) {
        this.loginJanela = loginJanela;
    }

    @FXML
    private void handleCallSignup() {
        // abrir nova tela
        new Signup().show();

        // fechar esta
        loginJanela.close();
    }
    @FXML
    private void handleExitAction() {
        loginJanela.close();
    }

    @FXML
    private void handleLoginAction() {

        String id = usuarioComboBox.getValue().getId();
        String password = passwordField.getText();

        Usuario usuario = UsuarioDAO.buscarUsuarioPorId(id);

        assert usuario != null;

        boolean senhaValida = SenhaUtil.verificaSenha(password, usuario.getSenha());

        if (senhaValida) {
            messageLabel.setText("Login bem-sucedido!");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            messageLabel.setText("Usuário ou senha incorretos.");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    @FXML
    private void initialize() {

        List<Usuario> usuarios = UsuarioDAO.buscarUsuarios();

        for(Usuario usuario : usuarios){
            usuarioComboBox.getItems().add(usuario);
        }

        usuarioComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Usuario usuario, boolean empty){
                super.updateItem(usuario, empty);
                if (empty || usuario == null) {
                    setText(null);
                } else {
                    setText(usuario.getNome());
                }
            }
        });

        usuarioComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Usuario usuario, boolean empty) {
                super.updateItem(usuario, empty);
                if (empty || usuario == null) {
                    setText(null);
                } else {
                    setText(usuario.getNome());
                }
            }
        });
    }

}
