package com.example.ogestor.componentes.base;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseJanela {

    protected Stage stage;
    private static final Logger LOGGER = Logger.getLogger(BaseJanela.class.getName());
    public BaseJanela(String fxmlPath) {
        this.stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            stage.setScene(new Scene(root));
            configurarJanelaBase();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha ao carregar a tela: " + fxmlPath, e);
        }
    }

    protected void configurarJanelaBase() {
        stage.getIcons().add(new javafx.scene.image.Image("/com/example/ogestor/assets/img/icon.png"));
        // Outras configurações globais
    }

    public void show () {
        stage.show();
    }

    public void close () {
        stage.close();
    }

    protected Stage getStage() {
        return stage;
    }
}
