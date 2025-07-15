package com.example.ogestor.reports.vendas;

import com.example.ogestor.DTO.EmpresaDTO;
import com.example.ogestor.api.DadosEmpresaService;
import com.example.ogestor.exception.ErrorDefault;
import com.example.ogestor.exception.ErrorLogger;
import com.example.ogestor.util.ImageUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReportVenda {

    public void reportTable(String nameReport) {

        try{
            String modelPath = Objects.requireNonNull(getClass().getResource(
                    "/com/example/ogestor/reports/vendas/modelReportVenda.jasper")
            ).getPath();

            DadosEmpresaService dadosEmpresa = new DadosEmpresaService();
            var header = dadosEmpresa.getEmpresa();

            if (header.isPresent()) {
                Map<String, Object> parameters = new HashMap<>();

                EmpresaDTO dados = header.get();

                parameters.put("NOME_EMPRESA", dados.nome);
                parameters.put("LOGO", ImageUtil.converterBase64ParaImage(dados.logo.logo_base64));
                parameters.put("CNPJ", dados.cnpj);
                parameters.put("INSCRICAO_ESTADUAL", dados.inscricaoEstadual);
                parameters.put("TELEFONE", dados.telefone);
                parameters.put("EMAIL", dados.email);
                parameters.put("RUA", dados.endereco.rua);
                parameters.put("NUMERO", dados.endereco.numero);
                parameters.put("BAIRRO", dados.endereco.bairro);
                parameters.put("CIDADE", dados.endereco.cidade);
                parameters.put("UF", dados.endereco.siglaEstado);
                parameters.put("CEP", dados.endereco.cep);
                parameters.put("TITULO_RELATORIO", nameReport);

                JRDataSource dataSourceVazio = new JREmptyDataSource();
                JasperPrint print = JasperFillManager.fillReport(modelPath, parameters, dataSourceVazio);

                JasperViewer.viewReport(print, false);
            }else {
                throw new Exception("Erro ao buscar os dados da empresa");
            }


        } catch (Exception e) {
            ErrorDefault erro = ErrorLogger.tratarErro(e, e.getMessage());
            System.out.println(erro.getMensagemDesenvolvedor());
        }


    }
}
