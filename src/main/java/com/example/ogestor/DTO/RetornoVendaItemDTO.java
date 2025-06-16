package com.example.ogestor.DTO;


public class RetornoVendaItemDTO {
    private int item_cod;
    private int venda;
    private int cod_produto;
    private String descricao;
    private double qtd;
    private double qtd_devolvida;
    private double custo;
    private double desconto;
    private double total;
    private String data_venda;
    private int cod_vendedor;
    private String nome_vendedor;
    private double despesa_fixa;
    private double despesa_variavel;
    private double comissao;
    private double lucro;
    private double lucro_percentual;

    public int getItem_cod() {
        return item_cod;
    }

    public void setItem_cod(int item_cod) {
        this.item_cod = item_cod;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getQtd_devolvida() {
        return qtd_devolvida;
    }

    public void setQtd_devolvida(double qtd_devolvida) {
        this.qtd_devolvida = qtd_devolvida;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public int getCod_vendedor() {
        return cod_vendedor;
    }

    public void setCod_vendedor(int cod_vendedor) {
        this.cod_vendedor = cod_vendedor;
    }

    public String getNome_vendedor() {
        return nome_vendedor;
    }

    public void setNome_vendedor(String nome_vendedor) {
        this.nome_vendedor = nome_vendedor;
    }

    public double getDespesa_fixa() {
        return despesa_fixa;
    }

    public void setDespesa_fixa(double despesa_fixa) {
        this.despesa_fixa = despesa_fixa;
    }

    public double getDespesa_variavel() {
        return despesa_variavel;
    }

    public void setDespesa_variavel(double despesa_variavel) {
        this.despesa_variavel = despesa_variavel;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public double getLucro_percentual() {
        return lucro_percentual;
    }

    public void setLucro_percentual(double lucro_percentual) {
        this.lucro_percentual = lucro_percentual;
    }
}
