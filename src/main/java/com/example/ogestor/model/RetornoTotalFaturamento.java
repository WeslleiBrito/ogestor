package com.example.ogestor.model;

import java.math.BigDecimal;

public class RetornoTotalFaturamento {
    private final BigDecimal faturamento;
    private final BigDecimal custo;
    private final BigDecimal comissao;
    private final BigDecimal despesa_variavel;
    private final BigDecimal despesa_fixa;
    private final BigDecimal lucro;
    private final BigDecimal lucro_percentual;

    public RetornoTotalFaturamento(BigDecimal faturamento, BigDecimal custo, BigDecimal comissao, BigDecimal despesa_variavel,
                                   BigDecimal despesa_fixa, BigDecimal lucro, BigDecimal lucro_percentual) {
        this.faturamento = faturamento;
        this.custo = custo;
        this.comissao = comissao;
        this.despesa_variavel = despesa_variavel;
        this.despesa_fixa = despesa_fixa;
        this.lucro = lucro;
        this.lucro_percentual = lucro_percentual;
    }

    public BigDecimal getFaturamento() {
        return faturamento;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public BigDecimal getDespesa_variavel() {
        return despesa_variavel;
    }

    public BigDecimal getDespesa_fixa() {
        return despesa_fixa;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public BigDecimal getLucro_percentual() {
        return lucro_percentual;
    }

    @Override
    public String toString() {
        return "RetornoTotalFaturamento{" +
                "faturamento=" + faturamento +
                ", custo=" + custo +
                ", comissao=" + comissao +
                ", despesaVariavel=" + despesa_variavel +
                ", despesaFixa=" + despesa_fixa +
                ", lucro=" + lucro +
                ", lucroPercentual=" + lucro_percentual +
                '}';
    }
}
