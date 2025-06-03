package com.example.ogestor.componentes;

import com.example.ogestor.componentes.base.BaseJanela;
import com.example.ogestor.controller.LoginController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Login extends BaseJanela {

    public Login() {
        super(); // Não carrega diretamente via BaseJanela
        configurarJanela();
    }
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());

    private void configurarJanela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ogestor/windows/login/login.fxml"));
            Parent root = loader.load();

            // Pega o controller e injeta a instância Login
            LoginController controller = loader.getController();
            controller.setLoginJanela(this);

            // Define cena e configurações do Stage
            getStage().setScene(new Scene(root));
            getStage().setResizable(false);
            getStage().setWidth(300);
            getStage().setHeight(400);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar janela de cadastro", e);
        }
    }
}
