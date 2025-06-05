package com.example.ogestor.controller;

import com.example.ogestor.componentes.Home;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HomeController {

    @FXML private Label iconeCard;
    @FXML private Label lblIconeFaturamento;
    @FXML Label lblFaturamento;
    private Home janelaHome;

    public void setJanelaHome(Home janelaHome) {
        this.janelaHome = janelaHome;
    }
}
