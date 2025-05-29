package com.example.ogestor.model;

import java.util.List;

public class ResumoFinanceiro {
    private final double faturamento;
    private final double custo;
    private final double desconto;
    private final double despesa_fixa;
    private final double despesa_variavel;
    private final double lucro_rs;
    private final double lucro_percentual;
    private final List<String> periodo;
    
    
    public ResumoFinanceiro (
            double faturamento,
            double custo,
            double desconto,
            double despesaFixa,
            double despesaVariavel,
            double lucroRS,
            double lucroPorcentagem,
            List<String> periodo
    ){
        this.faturamento = faturamento;
        this.custo = custo;
        this.desconto = desconto;
        this.despesa_fixa = despesaFixa;
        this.despesa_variavel = despesaVariavel;
        this.lucro_rs = lucroRS;
        this.lucro_percentual = lucroPorcentagem;
        this.periodo = periodo;
        
    }

    public double getFaturamento() {
        return faturamento;
    }

    public double getCusto() {
        return custo;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getDespesaFixa() {
        return despesa_fixa;
    }

    public double getDespesaVariavel() {
        return despesa_variavel;
    }

    public double getLucroRs() {
        return lucro_rs;
    }

    public double getLucroPercentual() {
        return lucro_percentual;
    }

    public List<String> getPeriodo() {
        return periodo;
    }

    @Override
    public String toString() {
        return "ResumoFinanceiro{" +
                "faturamento=" + faturamento +
                ", custo=" + custo +
                ", desconto=" + desconto +
                ", despesa_fixa=" + despesa_fixa +
                ", despesa_variavel=" + despesa_variavel +
                ", lucro_rs=" + lucro_rs +
                ", lucro_percentual=" + lucro_percentual +
                ", periodo=" + periodo +
                '}';
    }
}
