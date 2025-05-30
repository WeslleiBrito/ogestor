package com.example.ogestor.router;

import com.example.ogestor.componentes.Login;
import com.example.ogestor.model.Screen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenRouter {

    public static Login login() {
        return new Login();
    }
}
