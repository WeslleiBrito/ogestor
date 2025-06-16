package com.example.ogestor.model;

import com.example.ogestor.DTO.RetornoVendaItemDTO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RetornoVendaItem {
    private final SimpleIntegerProperty item_cod;
    private final SimpleIntegerProperty venda;
    private final SimpleIntegerProperty cod_produto;
    private final SimpleStringProperty descricao;
    private final SimpleDoubleProperty qtd;
    private final SimpleDoubleProperty qtd_devolvida;
    private final SimpleDoubleProperty custo;
    private final SimpleDoubleProperty desconto;
    private final SimpleDoubleProperty total;
    private final SimpleStringProperty data_venda;
    private final SimpleIntegerProperty cod_vendedor;
    private final SimpleStringProperty nome_vendedor;
    private final SimpleDoubleProperty despesa_fixa;
    private final SimpleDoubleProperty despesa_variavel;
    private final SimpleDoubleProperty comissao;
    private final SimpleDoubleProperty lucro;
    private final SimpleDoubleProperty lucro_percentual;


    public RetornoVendaItem(SimpleIntegerProperty item_cod, SimpleIntegerProperty venda, SimpleIntegerProperty cod_produto, SimpleStringProperty descricao, SimpleDoubleProperty qtd, SimpleDoubleProperty qtd_devolvida, SimpleDoubleProperty custo, SimpleDoubleProperty desconto, SimpleDoubleProperty total, SimpleStringProperty data_venda, SimpleIntegerProperty cod_vendedor, SimpleStringProperty nome_vendedor, SimpleDoubleProperty despesa_fixa, SimpleDoubleProperty despesa_variavel, SimpleDoubleProperty comissao, SimpleDoubleProperty lucro, SimpleDoubleProperty lucro_percentual) {
        this.item_cod = item_cod;
        this.venda = venda;
        this.cod_produto = cod_produto;
        this.descricao = descricao;
        this.qtd = qtd;
        this.qtd_devolvida = qtd_devolvida;
        this.custo = custo;
        this.desconto = desconto;
        this.total = total;
        this.data_venda = data_venda;
        this.cod_vendedor = cod_vendedor;
        this.nome_vendedor = nome_vendedor;
        this.despesa_fixa = despesa_fixa;
        this.despesa_variavel = despesa_variavel;
        this.comissao = comissao;
        this.lucro = lucro;
        this.lucro_percentual = lucro_percentual;
    }

    public Integer getItemCod() {
        return item_cod.getValue();
    }

    public Integer getVenda() {
        return venda.getValue();
    }

    public Integer getCodProduto() {
        return cod_produto.getValue();
    }

    public String getDescricao() {
        return descricao.getValue();
    }

    public BigDecimal getQtd() {
        return new BigDecimal(qtd.getValue().toString());
    }

    public BigDecimal getQtdDevolvida() {
        return new BigDecimal(qtd_devolvida.getValue().toString());
    }

    public BigDecimal getCusto() {
        return new BigDecimal(custo.getValue().toString());
    }

    public BigDecimal getDesconto() {
        return new BigDecimal(desconto.getValue().toString());
    }

    public BigDecimal getTotal() {
        return new BigDecimal(total.getValue().toString());
    }

    public LocalDate getDataVenda() {
        return LocalDate.parse(data_venda.getValue());
    }

    public Integer getCodVendedor() {
        return cod_vendedor.getValue();
    }

    public String getNomeVendedor() {
        return nome_vendedor.getValue();
    }

    public BigDecimal getDespesaFixa() {
        return new BigDecimal(despesa_fixa.getValue().toString());
    }

    public BigDecimal getDespesaVariavel() {
        return new BigDecimal(despesa_variavel.getValue().toString());
    }

    public BigDecimal getComissao() {
        return new BigDecimal(comissao.getValue().toString());
    }

    public BigDecimal getLucro() {
        return new BigDecimal(lucro.getValue().toString());
    }

    public BigDecimal getLucroPercentual() {
        return new BigDecimal(lucro_percentual.getValue().toString());
    }

    public static RetornoVendaItem fromDTO(RetornoVendaItemDTO dto) {
        return new RetornoVendaItem(
                new SimpleIntegerProperty(dto.getItem_cod()),
                new SimpleIntegerProperty(dto.getVenda()),
                new SimpleIntegerProperty(dto.getCod_produto()),
                new SimpleStringProperty(dto.getDescricao()),
                new SimpleDoubleProperty(dto.getQtd()),
                new SimpleDoubleProperty(dto.getQtd_devolvida()),
                new SimpleDoubleProperty(dto.getCusto()),
                new SimpleDoubleProperty(dto.getDesconto()),
                new SimpleDoubleProperty(dto.getTotal()),
                new SimpleStringProperty(dto.getData_venda()),
                new SimpleIntegerProperty(dto.getCod_vendedor()),
                new SimpleStringProperty(dto.getNome_vendedor()),
                new SimpleDoubleProperty(dto.getDespesa_fixa()),
                new SimpleDoubleProperty(dto.getDespesa_variavel()),
                new SimpleDoubleProperty(dto.getComissao()),
                new SimpleDoubleProperty(dto.getLucro()),
                new SimpleDoubleProperty(dto.getLucro_percentual())
        );
    }
}
