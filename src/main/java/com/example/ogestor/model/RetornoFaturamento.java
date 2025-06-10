package com.example.ogestor.model;

import java.math.BigDecimal;
import java.util.List;

public class RotornoFaturamento {
    private final BigDecimal faturamento;
    private final BigDecimal custo;
    private final List<String> periodo;

    public RotornoFaturamento(BigDecimal faturamento, BigDecimal custo, List<String> periodo) {
        this.faturamento = faturamento;
        this.custo = custo;
        this.periodo = periodo;
    }

    public BigDecimal getFaturamento() {
        return faturamento;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public List<String> getPeriodo() {
        return periodo;
    }
}
