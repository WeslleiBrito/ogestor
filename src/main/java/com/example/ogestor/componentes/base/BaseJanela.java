package com.example.ogestor.componentes.base;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseJanela {

    protected Stage stage;
    private static final Logger LOGGER = Logger.getLogger(BaseJanela.class.getName());

    public BaseJanela() {
        this.stage = new Stage();
        configurarJanelaBase();
    }

    private void configurarJanelaBase() {
        try {
            URL iconURL = getClass().getResource("/com/example/ogestor/icons/worker.png");
            if (iconURL != null) {
                stage.getIcons().add(new Image(iconURL.toString()));
            } else {
                LOGGER.warning("Ícone da janela não encontrado.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Falha ao configurar o ícone da janela.", e);
        }

        // Outras configurações globais futuras podem vir aqui
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
    }

    protected Stage getStage() {
        return stage;
    }
}
