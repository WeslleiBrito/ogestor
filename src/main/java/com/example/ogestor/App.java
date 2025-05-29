package com.example.ogestor;

import com.example.ogestor.componentes.Login;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.ogestor.database.Database;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Database.inicializar();

        Login login = new Login(primaryStage);
        login.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}