package com.example.ogestor.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RetornoVendaItem {
    private final int item_cod;
    private final int venda;
    private final int cod_produto;
    private final String descricao;
    private final BigDecimal qtd;
    private final BigDecimal qtd_devolvida;
    private final BigDecimal custo;
    private final BigDecimal desconto;
    private final BigDecimal total;
    private final String data_venda;
    private final int cod_vendedor;
    private final String nome_vendedor;
    private final BigDecimal despesa_fixa;
    private final BigDecimal despesa_variavel;
    private final BigDecimal comissao;
    private final BigDecimal lucro;
    private final BigDecimal lucro_percentual;


    public RetornoVendaItem(int item_cod, int venda, int cod_produto, String descricao, BigDecimal qtd, BigDecimal qtd_devolvida, BigDecimal custo, BigDecimal desconto, BigDecimal total, String data_venda, int cod_vendedor, String nome_vendedor, BigDecimal despesa_fixa, BigDecimal despesa_variavel, BigDecimal comissao, BigDecimal lucro, BigDecimal lucro_percentual) {
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
        return item_cod;
    }

    public Integer getVenda() {
        return venda;
    }

    public Integer getCodProduto() {
        return cod_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public BigDecimal getQtdDevolvida() {
        return qtd_devolvida;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDate getDataVenda() {
        return LocalDate.parse(data_venda);
    }

    public Integer getCodVendedor() {
        return cod_vendedor;
    }

    public String getNomeVendedor() {
        return nome_vendedor;
    }

    public BigDecimal getDespesaFixa() {
        return despesa_fixa;
    }

    public BigDecimal getDespesaVariavel() {
        return despesa_variavel;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public BigDecimal getLucroPercentual() {
        return lucro_percentual;
    }
}
