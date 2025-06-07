package com.example.ogestor.controller;

import com.example.ogestor.api.ResumoService;
import com.example.ogestor.componentes.Home;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
            var resumo = resumoService.getResumoFinanceiro();

            if(resumo.isPresent()) {

                Locale locale = Locale.forLanguageTag("pt-BR");
                NumberFormat moedaBr = NumberFormat.getCurrencyInstance(locale);
                NumberFormat numeroBr = NumberFormat.getNumberInstance(locale);
                lblValorFaturamento.setText(moedaBr.format(resumo.get().getFaturamento()));
                lblValorCusto.setText(moedaBr.format(resumo.get().getCusto()));
                lblValorDespesaFixa.setText(moedaBr.format(resumo.get().getDespesaFixa()));
                lblValorVariavel.setText(moedaBr.format(resumo.get().getDespesaVariavel()));
                lblValorLucroMonetario.setText(moedaBr.format(resumo.get().getLucroRs()));
                lblValorLucroPercentual.setText(
                        numeroBr.format(resumo.get().getLucroPercentual().multiply(new BigDecimal("100")))
                                + " %");
            }else {
                throw new Exception("Erro de conexão com a API");
            }

        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar os dados da API", e);
        }
    }
}
