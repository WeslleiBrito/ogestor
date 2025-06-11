package com.example.ogestor.controller;

import com.example.ogestor.api.ResumoService;
import com.example.ogestor.componentes.Home;
import com.example.ogestor.model.ResumoFinanceiro;
import com.example.ogestor.model.RetornoFaturamento;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomeController {

    @FXML private ListView<String> listaResumoFaturamento;
    @FXML private DatePicker dataFinal;
    @FXML private DatePicker dataInicial;
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
    private void atualizarValorTotal() {
        try {
            var resumo = buscarDadosValoresTotalPadrao();

            if(resumo.isPresent()) {
                updateValorTotalMensal(resumo.get());
            }else {
                throw new Exception("Erro de conexão com a API");
            }
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @FXML
    private void buscarValorTotalPorData(){

        try {
            ResumoService resumoService = new ResumoService();

            var resumo = resumoService.getFaturamento(
                    dataInicial.getValue(), dataFinal.getValue()
            );

            if(resumo.isPresent()) {
                updateFaturamento(resumo.get());
            }else {
                throw new Exception("Erro de conexão com a API");
            }
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @FXML
    private void initialize() {
        try {
            var resumo = buscarDadosValoresTotalPadrao();
            this.updateResumoListaFaturamento();
            if(resumo.isPresent()) {
                updateValorTotalMensal(resumo.get());
            }else {
                throw new Exception("Erro de conexão com a API");
            }

        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private Optional<ResumoFinanceiro> buscarDadosValoresTotalPadrao() {
        ResumoService resumoService = new ResumoService();
        return resumoService.getResumoFinanceiro();
    }

    private void updateValorTotalMensal(@NotNull ResumoFinanceiro resumo) {
        Locale locale = Locale.forLanguageTag("pt-BR");
        NumberFormat moedaBr = NumberFormat.getCurrencyInstance(locale);
        NumberFormat numeroBr = NumberFormat.getNumberInstance(locale);
        lblValorFaturamento.setText(moedaBr.format(resumo.getFaturamento()));
        lblValorCusto.setText(moedaBr.format(resumo.getCusto()));
        lblValorDespesaFixa.setText(moedaBr.format(resumo.getDespesaFixa()));
        lblValorVariavel.setText(moedaBr.format(resumo.getDespesaVariavel()));
        lblValorLucroMonetario.setText(moedaBr.format(resumo.getLucroRs()));
        lblValorLucroPercentual.setText(
                numeroBr.format(resumo.getLucroPercentual().multiply(new BigDecimal("100")))
                        + " %");
    }

    private void updateFaturamento(@NotNull RetornoFaturamento retornoFaturamento) {
        Locale locale = Locale.forLanguageTag("pt-BR");
        NumberFormat moedaBr = NumberFormat.getCurrencyInstance(locale);
        NumberFormat numeroBr = NumberFormat.getNumberInstance(locale);

        lblValorFaturamento.setText(moedaBr.format(retornoFaturamento.getFaturamento()));
        lblValorCusto.setText(moedaBr.format(retornoFaturamento.getCusto()));
    }

    private void updateResumoListaFaturamento() {

        listaResumoFaturamento.getItems().add("Teste 1");
        listaResumoFaturamento.getItems().add("Teste 2");
        listaResumoFaturamento.getItems().add("Teste 3");
        listaResumoFaturamento.getItems().add("Teste 4");
    }

}
