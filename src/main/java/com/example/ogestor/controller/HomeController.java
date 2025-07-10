package com.example.ogestor.controller;

import com.example.ogestor.api.*;
import com.example.ogestor.componentes.Home;
import com.example.ogestor.model.ResumoFinanceiro;
import com.example.ogestor.model.RetornoTotalFaturamento;
import com.example.ogestor.model.RetornoVendaItem;
import com.example.ogestor.model.RetornoVendedor;
import com.example.ogestor.reports.vendas.ReportVenda;
import com.example.ogestor.util.ImageUtil;
import com.example.ogestor.util.LimpaNomeVendedor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomeController {


    @FXML private Label comissaoMensal;
    @FXML private BarChart<String, Number> barChartVendedor;
    @FXML private Label labelBuscar;
    @FXML private Button btnBuscar;
    @FXML private ProgressIndicator indicadorBuscar;
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
    private void buscarValorTotalPorData() {
        indicadorBuscar.setVisible(true);
        btnBuscar.setDisable(true);
        labelBuscar.setText("Buscando...");
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                ResumoTotalFaturamentoService resumoFaturamento = new ResumoTotalFaturamentoService();
                VendaItemService vendaItem = new VendaItemService();
                VendedorService vendedorService = new VendedorService();
                ResumoFinanceiroService resumoFinanceiroService = new ResumoFinanceiroService();

                Optional<RetornoTotalFaturamento> resumo;
                Optional<List<RetornoVendaItem>> retornoVendaItem;
                Optional<List<RetornoVendedor>> retornoVendedor;
                Optional<ResumoFinanceiro> resumoMensal;
                
                if (dataInicial.getValue() == null && dataFinal.getValue() == null) {
                    resumo = resumoFaturamento.getResumoTotalFaturamento();
                    retornoVendaItem = vendaItem.getVendaItem();
                    retornoVendedor = vendedorService.getVendasVendedor();
                    resumoMensal = resumoFinanceiroService.getResumoFinanceiro();
                } else if (dataInicial.getValue() == null && dataFinal.getValue() != null) {
                    resumo = resumoFaturamento.getResumoTotalFaturamentoDataFinal(dataFinal.getValue());
                    retornoVendaItem = vendaItem.getVendaItemDataFinal(dataFinal.getValue());
                    retornoVendedor = vendedorService.getVendasVendedorDataFinal(dataFinal.getValue());
                    resumoMensal = resumoFinanceiroService.getResumoFinanceiroDataFinal(dataFinal.getValue());
                } else if (dataFinal.getValue() == null && dataInicial.getValue() != null) {
                    resumo = resumoFaturamento.getResumoTotalFaturamentoDataIncial(dataInicial.getValue());
                    retornoVendaItem = vendaItem.getVendaItemDataInicial(dataInicial.getValue());
                    retornoVendedor = vendedorService.getVendasVendedorDataInicial(dataInicial.getValue());
                    resumoMensal = resumoFinanceiroService.getResumoFinanceiroDataFinal(dataInicial.getValue());
                } else {
                    resumo = resumoFaturamento.getResumoTotalFaturamento(dataInicial.getValue(), dataFinal.getValue());
                    retornoVendaItem = vendaItem.getVendaItem(dataInicial.getValue(), dataFinal.getValue());
                    retornoVendedor = vendedorService.getVendasVendedor(dataInicial.getValue(), dataFinal.getValue());
                    resumoMensal = resumoFinanceiroService.getResumoFinanceiro(dataInicial.getValue(), dataFinal.getValue());
                }

                if (resumo.isPresent()) {
                    Platform.runLater(() -> updateValorDiario(resumo.get()));
                } else {
                    throw new Exception("Erro de conexão com a API (resumo)");
                }

                if (retornoVendaItem.isPresent()) {
                    Platform.runLater(() -> updateTabelaVendaItens(retornoVendaItem.get()));
                } else {
                    throw new Exception("Erro de conexão com a API (itens)");
                }

                if (retornoVendedor.isPresent()) {
                    Platform.runLater(() -> updateGraficoVendedor(retornoVendedor.get()));
                } else {
                    throw new Exception("Erro de conexão com ao buscar os dados dos vendedores.");
                }
                
                
                if(resumoMensal.isPresent()) {
                    Platform.runLater(() -> updateValorTotalMensal(resumoMensal.get()));
                }else {
                    System.out.println(resumoMensal);
                    throw new Exception("Erro ao buscar os dados do resumo mensal.");
                }
                return null;
            }

            @Override
            protected void succeeded() {
                indicadorBuscar.setVisible(false);
                btnBuscar.setDisable(false);
                labelBuscar.setText("Buscar");
            }

            @Override
            protected void failed() {
                indicadorBuscar.setVisible(false);
                btnBuscar.setDisable(false);
                labelBuscar.setText("Buscar");
                Throwable ex = getException();
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            }
        };

        new Thread(task).start(); // executa em background
    }

    @FXML
    private void initialize() {
        try {

            var resumoDiario = buscarValorPorData();
            ReportVenda reportVenda = new ReportVenda();

            //reportVenda.reportTable();

            if(resumoDiario.isPresent()) {
                updateValorDiario(resumoDiario.get());
            }else {
                throw new Exception("Erro ao buscar os dados do faturamento diário");
            }

            var vendaItem = buscarValoresVendaItem();

            if(vendaItem.isPresent()) {
                updateTabelaVendaItens(vendaItem.get());
            }else {
                throw new Exception("Erro ao buscar os dados do faturamento diario");
            }

            var vendaVendedor = buscarVendaVendedor();

            if(vendaVendedor.isPresent()) {
                updateGraficoVendedor(vendaVendedor.get());
            }else {
                throw new Exception("Erro ao buscar os dados do faturamento diario");
            }

            var resumoMensal = buscarValoresResumoMensal();

            if(resumoMensal.isPresent()) {
                updateValorTotalMensal(resumoMensal.get());
            }else {
                throw new Exception("Erro ao buscar os dados do faturamento diario");
            }

        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private Optional<ResumoFinanceiro> buscarDadosValoresTotalPadrao() {
        ResumoFinanceiroService resumoFinanceiroService = new ResumoFinanceiroService();
        return resumoFinanceiroService.getResumoFinanceiro();
    }

    private Optional<List<RetornoVendedor>> buscarVendaVendedor() {
        VendedorService vendedorService = new VendedorService();
        return vendedorService.getVendasVendedor();
    }

    private Optional<RetornoTotalFaturamento> buscarValorPorData() {
        ResumoTotalFaturamentoService resumoFaturamento = new ResumoTotalFaturamentoService();
        return resumoFaturamento.getResumoTotalFaturamento();
    }

    private Optional<List<RetornoVendaItem>> buscarValoresVendaItem() {
        VendaItemService vendaItem = new VendaItemService();
        return vendaItem.getVendaItem();
    }

    private Optional<ResumoFinanceiro> buscarValoresResumoMensal() {
        ResumoFinanceiroService resumoMensal = new ResumoFinanceiroService();
        return resumoMensal.getResumoFinanceiro();
    }

    private void updateValorTotalMensal(@NotNull ResumoFinanceiro resumo) {
        faturamentoMensal.setText("Faturamento: " + moedaBr.format(resumo.getFaturamento()));
        custoMensal.setText("Custo: " + moedaBr.format(resumo.getCusto()));
        despesaVariavelMensal.setText("Despesa variável: " + moedaBr.format(resumo.getDespesaVariavel()));
        comissaoMensal.setText("Comissão: " + moedaBr.format(resumo.getComissao()));
        despesaFixaMensal.setText("Despesa Fixa: " + moedaBr.format(resumo.getDespesaFixa()));
        lucroRsMensal.setText("Lucro Monetário: " + moedaBr.format(resumo.getLucroRs()));
        lucroPercentualMensal.setText("Lucro %: " +
                numeroBr.format(resumo.getLucroPercentual().multiply(new BigDecimal("100")))
                        + " %");
    }

    private void updateValorDiario(@NotNull RetornoTotalFaturamento retorno) {
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

    private void updateTabelaVendaItens(@NotNull List<RetornoVendaItem> retorno) {

        tabelaVendaItens.getItems().clear();

        colVenda.setCellValueFactory(new PropertyValueFactory<>("venda"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        colVendedor.setCellValueFactory(new PropertyValueFactory<>("nomeVendedor"));
        colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("codProduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        colCusto.setCellValueFactory(new PropertyValueFactory<>("custo"));
        colComissao.setCellValueFactory(new PropertyValueFactory<>("comissao"));
        colDespesaVariavel.setCellValueFactory(new PropertyValueFactory<>("despesaVariavel"));
        colDespesaFixa.setCellValueFactory(new PropertyValueFactory<>("despesaFixa"));
        colFaturamento.setCellValueFactory(new PropertyValueFactory<>("total"));
        colLucroRS.setCellValueFactory(new PropertyValueFactory<>("lucro"));
        colLucroPercentual.setCellValueFactory(new PropertyValueFactory<>("lucroPercentual"));

        colQuantidade.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(numeroBr.format(value));
                }
            }
        });

        colData.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    setText(date.format(formatter));
                }
            }
        });

        colCusto.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(moedaBr.format(value));
                }
            }
        });

        colComissao.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(moedaBr.format(value));
                }
            }
        });

        colDespesaVariavel.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(moedaBr.format(value));
                }
            }
        });

        colDespesaFixa.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(moedaBr.format(value));
                }
            }
        });

        colFaturamento.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(moedaBr.format(value));
                }
            }
        });

        colLucroRS.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(moedaBr.format(value));
                }
            }
        });

        colLucroPercentual.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(numeroBr.format(value.multiply(new BigDecimal("100"))));
                }
            }
        });

        colVendedor.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(LimpaNomeVendedor.nomeFormatado(value, true));
                }
            }
        });
        ObservableList<RetornoVendaItem> retornoVendaItems = FXCollections.observableArrayList(retorno);

        tabelaVendaItens.setItems(retornoVendaItems);
    }

    private void updateGraficoVendedor(@NotNull List<RetornoVendedor> retorno) {
        barChartVendedor.getData().clear();

        XYChart.Series<String, Number> serieLucro = new XYChart.Series<>();
        serieLucro.setName("Lucro");

        XYChart.Series<String, Number> serieFaturamento = new XYChart.Series<>();
        serieFaturamento.setName("Faturamento");

        XYChart.Series<String, Number> serieLucroPercentual = new XYChart.Series<>();
        serieLucroPercentual.setName("Lucro %");

        for(RetornoVendedor vendedor : retorno) {

            String nome = LimpaNomeVendedor.nomeFormatado(vendedor.getVendedorDescricao(), false);

            barChartVendedor.getData().clear();

            serieFaturamento.getData().add(new XYChart.Data<>(nome, vendedor.getFaturamento()));
            serieLucro.getData().add(new XYChart.Data<>(nome, vendedor.getLucro()));
            serieLucroPercentual.getData().add(new XYChart.Data<>(nome, vendedor.getLucroPercentual().multiply(new BigDecimal("100"))));

        }

        barChartVendedor.getData().add(serieFaturamento);
        barChartVendedor.getData().add(serieLucro);
        barChartVendedor.getData().add(serieLucroPercentual);
    }

}
