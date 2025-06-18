package com.example.ogestor.api;

import com.example.ogestor.model.ResumoFinanceiro;
import com.example.ogestor.model.RetornoFaturamento;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;


public class ResumoFinanceiroService extends BaseAPI {

    public Optional<ResumoFinanceiro> getResumoFinanceiro () {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "resumo-financeiro/"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {
               return Optional.ofNullable(
                       gson.fromJson(
                               response.body(),
                               ResumoFinanceiro.class
                       )
               );
            }else {
                System.err.println("Erro: código " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro: código " + e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<RetornoFaturamento> getFaturamento(LocalDate dataInicial, LocalDate dataFinal) {
        try {

            String url = String.format(
                    "%svendas/faturamento?data_inicial=%s&data_final=%s",
                    baseUrl,
                    dataInicial.toString(),
                    dataFinal.toString()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {
                return Optional.ofNullable(
                        gson.fromJson(
                                response.body(),
                                RetornoFaturamento.class
                        )
                );
            } else {
                System.err.println("Erro: código " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao acessar API: " + e.getMessage());
        }

        return Optional.empty();
    }
    

}
