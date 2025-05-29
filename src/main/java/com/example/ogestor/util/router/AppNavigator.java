package com.example.ogestor.util.router;

import com.example.ogestor.model.Screen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppNavigator {

    private static final Logger LOGGER = Logger.getLogger(AppNavigator.class.getName());

    public static void changeScene(Stage stage, Screen screen) {
        try {
            FXMLLoader loader = new FXMLLoader(AppNavigator.class.getResource(screen.getFxmlPath()));
            Parent root = loader.load();
            stage.setTitle(screen.getTitle());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Falha ao carregar a tela: " + screen.name(), e);
        }
    }
}
