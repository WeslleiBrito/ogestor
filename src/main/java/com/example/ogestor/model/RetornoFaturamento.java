package com.example.ogestor.model;

import java.math.BigDecimal;
import java.util.List;

public class RetornoFaturamento {
    private final BigDecimal faturamento;
    private final BigDecimal custo;
    private final BigDecimal desconto;
    private final List<String> periodo;

    public RetornoFaturamento(BigDecimal faturamento, BigDecimal custo, BigDecimal desconto, List<String> periodo) {
        this.faturamento = faturamento;
        this.custo = custo;
        this.desconto = desconto;
        this.periodo = periodo;
    }

    public BigDecimal getFaturamento() {
        return faturamento;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public List<String> getPeriodo() {
        return periodo;
    }
}
