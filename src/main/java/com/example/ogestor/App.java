package com.example.ogestor;

import com.example.ogestor.componentes.Login;
import com.example.ogestor.componentes.Signup;
import com.example.ogestor.router.ScreenRouter;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.ogestor.database.Database;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Database.inicializar();
        Login login = ScreenRouter.login();
        login.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}