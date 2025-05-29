package com.example.ogestor.componentes;

import com.example.ogestor.componentes.base.BaseJanela;
import javafx.stage.Stage;

public class Login extends BaseJanela {
    public Login(Stage stage) {
        super(stage, "/com/example/ogestor/windows/login/login.fxml");
    }

    @Override
    protected String getTitle() {
        return "Login";
    }

    @Override
    protected int getWidth() {
        return 270;
    }

    @Override
    protected int getHeight() {
        return 300;
    }
}
