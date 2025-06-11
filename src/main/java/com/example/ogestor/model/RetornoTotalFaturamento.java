package com.example.ogestor.model;

import java.math.BigDecimal;

public class RetornoTotalFaturamento {
    private final BigDecimal faturamento;
    private final BigDecimal custo;
    private final BigDecimal comissao;
    private final BigDecimal despesaVariavel;
    private final BigDecimal despesaFixa;
    private final BigDecimal lucro;
    private final BigDecimal lucroPercentual;

    public RetornoTotalFaturamento(BigDecimal faturamento, BigDecimal custo, BigDecimal comissao, BigDecimal despesaVariavel,
                                   BigDecimal despesaFixa, BigDecimal lucro, BigDecimal lucroPercentual) {
        this.faturamento = faturamento;
        this.custo = custo;
        this.comissao = comissao;
        this.despesaVariavel = despesaVariavel;
        this.despesaFixa = despesaFixa;
        this.lucro = lucro;
        this.lucroPercentual = lucroPercentual;
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

    public BigDecimal getDespesaVariavel() {
        return despesaVariavel;
    }

    public BigDecimal getDespesaFixa() {
        return despesaFixa;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public BigDecimal getLucroPercentual() {
        return lucroPercentual;
    }
}
