package com.example.ogestor.controller;

import com.example.ogestor.api.ResumoService;
import com.example.ogestor.componentes.Home;
import com.example.ogestor.model.ResumoFinanceiro;
import com.example.ogestor.model.RetornoFaturamento;
import com.example.ogestor.model.RetornoTotalFaturamento;
import com.example.ogestor.model.RetornoVendaItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomeController {


    @FXML private TableView<RetornoVendaItem> tabelaVendaItens;
    @FXML private TableColumn<RetornoVendaItem, String> colVendedor;
    @FXML private TableColumn<RetornoVendaItem, LocalDate> colData;
    @FXML private TableColumn<RetornoVendaItem, Integer> colVenda;
    @FXML private TableColumn<RetornoVendaItem, Integer> colCodigoProduto;
    @FXML private TableColumn<RetornoVendaItem, String> colProduto;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colQuantidade;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colCusto;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colComissao;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colDespesaVariavel;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colDespesaFixa;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colFaturamento;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colLucroRS;
    @FXML private TableColumn<RetornoVendaItem, BigDecimal> colLucroPercentual;
    @FXML private Label lucroPercentualMensal;
    @FXML private Label lucroRsMensal;
    @FXML private Label despesaVariavelMensal;
    @FXML private Label despesaFixaMensal;
    @FXML private Label custoMensal;
    @FXML private Label faturamentoMensal;
    @FXML private Label lblValorComissao;
    @FXML private DatePicker dataFinal;
    @FXML private DatePicker dataInicial;
    @FXML private Label lblValorLucroPercentual;
    @FXML private Label lblValorLucroMonetario;
    @FXML private Label lblValorVariavel;
    @FXML private Label lblValorDespesaFixa;
    @FXML private Label lblValorCusto;
    @FXML private Label lblValorFaturamento;
    private final Locale locale = Locale.forLanguageTag("pt-BR");
    private final NumberFormat moedaBr = NumberFormat.getCurrencyInstance(locale);
    private final NumberFormat numeroBr = NumberFormat.getNumberInstance(locale);

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

            var resumo = resumoService.getResumoTotalFaturamento(
                    dataInicial.getValue(), dataFinal.getValue()
            );

            if(resumo.isPresent()) {
                updateValorDiario(resumo.get());
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
            var resumoMensal = buscarDadosValoresTotalPadrao();

            if(resumoMensal.isPresent()) {
                updateValorTotalMensal(resumoMensal.get());
            }else {
                throw new Exception("Erro de conexão com a API");
            }

            var resumoDiario = buscarValorPorData();

            if(resumoDiario.isPresent()) {
                updateValorDiario(resumoDiario.get());
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

    private Optional<RetornoTotalFaturamento> buscarValorPorData() {
        ResumoService resumoService = new ResumoService();
        return resumoService.getResumoTotalFaturamento();
    }

    private void updateValorTotalMensal(@NotNull ResumoFinanceiro resumo) {

        faturamentoMensal.setText("Faturamento: " + moedaBr.format(resumo.getFaturamento()));
        custoMensal.setText("Custo " + moedaBr.format(resumo.getCusto()));
        despesaVariavelMensal.setText("Despesa variável: " + moedaBr.format(resumo.getDespesaVariavel()));
        despesaFixaMensal.setText("Despesa Fixa: " + moedaBr.format(resumo.getDespesaFixa()));
        lucroRsMensal.setText("Lucro Monetário: " + moedaBr.format(resumo.getLucroRs()));
        lucroPercentualMensal.setText("Lucro %: " +
                numeroBr.format(resumo.getLucroPercentual().multiply(new BigDecimal("100")))
                        + " %");
    }

    private void updateValorDiario(@NotNull RetornoTotalFaturamento retorno) {
        System.out.println(retorno);
        lblValorFaturamento.setText(moedaBr.format(retorno.getFaturamento()));
        lblValorCusto.setText(moedaBr.format(retorno.getCusto()));
        lblValorComissao.setText(moedaBr.format(retorno.getComissao()));
        lblValorVariavel.setText( moedaBr.format(retorno.getDespesa_variavel()));
        lblValorDespesaFixa.setText(moedaBr.format(retorno.getDespesa_fixa()));
        lblValorLucroMonetario.setText(moedaBr.format(retorno.getLucro()));
        lblValorLucroPercentual.setText(
                numeroBr.format(retorno.getLucro_percentual().multiply(new BigDecimal("100")))
                + " %");
    }

    private void updateTabelaVendaItens(@NotNull RetornoVendaItem retorno) {

        tabelaVendaItens.getItems().clear();

        colVendedor.setCellValueFactory(new PropertyValueFactory<>("cod_vendedor"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_venda"));
        colVenda.setCellValueFactory(new PropertyValueFactory<>("venda"));
        colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("cod_produto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        colCusto.setCellValueFactory(new PropertyValueFactory<>("custo"));
        colComissao.setCellValueFactory(new PropertyValueFactory<>("comissao"));
        colDespesaVariavel.setCellValueFactory(new PropertyValueFactory<>("despesa_variavel"));
        colDespesaFixa.setCellValueFactory(new PropertyValueFactory<>("despesa_fixa"));
        colFaturamento.setCellValueFactory(new PropertyValueFactory<>("total"));
        colLucroRS.setCellValueFactory(new PropertyValueFactory<>("lucro"));
        colLucroPercentual.setCellValueFactory(new PropertyValueFactory<>("lucro_percentual"));


        ObservableList<RetornoVendaItem> retornoVendaItems = FXCollections.observableArrayList(

        );
    }

}
