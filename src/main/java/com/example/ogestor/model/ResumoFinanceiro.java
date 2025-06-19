package com.example.ogestor.model;

import java.math.BigDecimal;
import java.util.List;

public class ResumoFinanceiro {
    private final BigDecimal faturamento;
    private final BigDecimal custo;
    private final BigDecimal desconto;
    private final BigDecimal despesa_fixa;
    private final BigDecimal despesa_variavel;
    private final BigDecimal lucro_rs;
    private final BigDecimal lucro_percentual;
    private final List<String> periodo;
    private final BigDecimal comissao;
    
    
    public ResumoFinanceiro (
            BigDecimal faturamento,
            BigDecimal custo,
            BigDecimal desconto,
            BigDecimal despesaFixa,
            BigDecimal despesaVariavel,
            BigDecimal comissao,
            BigDecimal lucroRS,
            BigDecimal lucroPorcentagem,
            List<String> periodo
    ){
        this.faturamento = faturamento;
        this.custo = custo;
        this.desconto = desconto;
        this.despesa_fixa = despesaFixa;
        this.despesa_variavel = despesaVariavel;
        this.comissao = comissao;
        this.lucro_rs = lucroRS;
        this.lucro_percentual = lucroPorcentagem;
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

    public BigDecimal getDespesaFixa() {
        return despesa_fixa;
    }

    public BigDecimal getDespesaVariavel() {
        return despesa_variavel;
    }

    public BigDecimal getLucroRs() {
        return lucro_rs;
    }

    public BigDecimal getLucroPercentual() {
        return lucro_percentual;
    }

    public List<String> getPeriodo() {
        return periodo;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    @Override
    public String toString() {
        return "ResumoFinanceiro{" +
                "faturamento=" + faturamento +
                ", custo=" + custo +
                ", desconto=" + desconto +
                ", despesa_fixa=" + despesa_fixa +
                ", despesa_variavel=" + despesa_variavel +
                ", despesa_variavel=" + comissao +
                ", lucro_rs=" + lucro_rs +
                ", lucro_percentual=" + lucro_percentual +
                ", periodo=" + periodo +
                '}';
    }
}
