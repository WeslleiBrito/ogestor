package com.example.ogestor.controller;

import com.example.ogestor.api.ResumoService;
import com.example.ogestor.componentes.Home;
import com.example.ogestor.model.ResumoFinanceiro;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javax.swing.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomeController {

    @FXML private Label lblValorLucroPercentual;
    @FXML private Label lblValorLucroMonetario;
    @FXML private Label lblValorVariavel;
    @FXML private Label lblValorDespesaFixa;
    @FXML private Label lblValorCusto;
    @FXML private Label lblValorFaturamento;
    private Home janelaHome;
    private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    public void setJanelaHome(Home janelaHome) {
        this.janelaHome = janelaHome;
    }

    @FXML
    private void initialize() {
        try {
            ResumoService resumoService = new ResumoService();
            ResumoFinanceiro resumoFinanceiro = resumoService.getResumoFinanceiro();
            Locale locale = Locale.forLanguageTag("pt-BR");
            NumberFormat moedaBr = NumberFormat.getCurrencyInstance(locale);
            NumberFormat numeroBr = NumberFormat.getNumberInstance(locale);
            lblValorFaturamento.setText(moedaBr.format(resumoFinanceiro.getFaturamento()));
            lblValorCusto.setText(moedaBr.format(resumoFinanceiro.getCusto()));
            lblValorDespesaFixa.setText(moedaBr.format(resumoFinanceiro.getDespesaFixa()));
            lblValorVariavel.setText(moedaBr.format(resumoFinanceiro.getDespesaVariavel()));
            lblValorLucroMonetario.setText(moedaBr.format(resumoFinanceiro.getLucroRs()));
            lblValorLucroPercentual.setText(
                    numeroBr.format(resumoFinanceiro.getLucroPercentual().multiply(new BigDecimal("100")))
            + " %");
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar os dados da API", e);
        }
    }
}
