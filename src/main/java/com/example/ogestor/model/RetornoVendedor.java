package com.example.ogestor.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RetornoVendedor {
    private final int cod_vendedor;
    private final String vendedor_descricao;
    private final BigDecimal desconto;
    private final BigDecimal custo;
    private final BigDecimal faturamento;
    private final BigDecimal despesa_fixa;
    private final BigDecimal despesa_variavel;
    private final BigDecimal comissao;
    private final BigDecimal lucro;
    private final BigDecimal lucro_percentual;
    private final BigDecimal quantidade_vendas;
    private final List<String>  data_venda;

    public RetornoVendedor(int cod_vendedor, String vendedor_descricao, BigDecimal desconto, BigDecimal custo, BigDecimal faturamento, BigDecimal despesa_fixa, BigDecimal despesa_variavel, BigDecimal comissao, BigDecimal lucro, BigDecimal lucro_percentual, BigDecimal quantidade_vendas, List<String> data_venda) {
        this.cod_vendedor = cod_vendedor;
        this.vendedor_descricao = vendedor_descricao;
        this.desconto = desconto;
        this.custo = custo;
        this.faturamento = faturamento;
        this.despesa_fixa = despesa_fixa;
        this.despesa_variavel = despesa_variavel;
        this.comissao = comissao;
        this.lucro = lucro;
        this.lucro_percentual = lucro_percentual;
        this.quantidade_vendas = quantidade_vendas;
        this.data_venda = data_venda;
    }

    public int getCodVendedor() {
        return cod_vendedor;
    }

    public String getVendedorDescricao() {
        return vendedor_descricao;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public BigDecimal getFaturamento() {
        return faturamento;
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

    public BigDecimal getQuantidadeVendas() {
        return quantidade_vendas;
    }

    public List<LocalDate> getDataVenda() {
        List<LocalDate> periodoVenda = new ArrayList<>();

        for(String data : data_venda){
            periodoVenda.add(LocalDate.parse(data));
        }
        return periodoVenda;
    }
}
