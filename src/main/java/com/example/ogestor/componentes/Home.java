package com.example.ogestor.componentes;

import com.example.ogestor.componentes.base.BaseJanela;
import com.example.ogestor.controller.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends BaseJanela {

    private static final Logger LOGGER = Logger.getLogger(Home.class.getName());

    public Home() {
        configurarJanela();
    }
    private void configurarJanela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ogestor/windows/home/home.fxml"));
            Parent root = loader.load();

            // Pega o controller e injeta a instância Login
            HomeController controller = loader.getController();
            controller.setJanelaHome(this);

            // Define cena e configurações do Stage
            getStage().setScene(new Scene(root));
            getStage().setMaximized(true);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar janela de cadastro", e);
        }
    }
}
