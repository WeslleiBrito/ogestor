package com.example.ogestor.componentes;

import com.example.ogestor.componentes.base.BaseJanela;
import com.example.ogestor.controller.SignupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Signup extends BaseJanela {

    public Signup() {
        super();
        configJanela();
    }

    private void configJanela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ogestor/windows/login/login.fxml"));
            Parent root = loader.load();

            // Pega o controller e injeta a instância Login
            SignupController controller = loader.getController();
            controller.setLoginJanela(this);

            // Define cena e configurações do Stage
            getStage().setScene(new Scene(root));
            getStage().setResizable(false);
            getStage().setWidth(300);
            getStage().setHeight(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
