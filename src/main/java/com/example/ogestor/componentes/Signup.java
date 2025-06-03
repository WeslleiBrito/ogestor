package com.example.ogestor.componentes;

import com.example.ogestor.componentes.base.BaseJanela;
import com.example.ogestor.controller.SignupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Signup extends BaseJanela {

    public Signup() {
        super();
        configJanela();
    }

    private static final Logger LOGGER = Logger.getLogger(Signup.class.getName());

    private void configJanela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ogestor/windows/signup/signup.fxml"));
            Parent root = loader.load();

            SignupController controller = loader.getController();
            controller.setJanelaSignup(this);

            getStage().setScene(new Scene(root));
            getStage().setResizable(false);
            getStage().setWidth(300);
            getStage().setHeight(430);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar janela de cadastro", e);
        }
    }
}
