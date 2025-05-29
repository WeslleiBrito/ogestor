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
    private static final Logger LOGGER = Logger.getLogger(BaseJanela.class.getName());
    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    public BaseJanela(Stage stage, String fxmlPath) {
        this.stage = stage;
        URL fxml = getClass().getResource(fxmlPath);
        Objects.requireNonNull(fxml, "Arquivo fxml não encontrado ou caminho incorreto. " + fxmlPath);

        try {
            FXMLLoader loader = new FXMLLoader(fxml);
            this.root = loader.load(); // corrigido
            this.scene = new Scene(this.root, getWidth(), getHeight());

            stage.setScene(scene);
            stage.setTitle(getTitle());
            stage.setResizable(isResizable());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getIconPath()))));

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha ao carregar a tela: " + fxmlPath, e);
        }
    }

    public void show() {
        stage.show();
    }

    protected int getWidth() {
        return 400;
    }

    protected int getHeight() {
        return 300;
    }

    protected boolean isResizable() {
        return false;
    }

    protected String getTitle() {
        return "Janela";
    }

    protected String getIconPath() {
        return "/com/example/ogestor/icons/worker.png";
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Parent getRoot() {
        return root;
    }
}
