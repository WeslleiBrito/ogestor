package com.example.ogestor.api;

import com.example.ogestor.model.ResumoFinanceiro;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;


public class ResumoFinanceiroService extends BaseAPI {

    public Optional<ResumoFinanceiro> getResumoFinanceiro (LocalDate dataInicial, LocalDate dataFinal) {
        try {

            String url = String.format(
                    "%sresumo-financeiro?data_inicial=%s&data_final=%s",
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

    public Optional<ResumoFinanceiro> getResumoFinanceiro() {
        return getResumoFinanceiro(LocalDate.now(), LocalDate.now());
    }
    public Optional<ResumoFinanceiro> getResumoFinanceiroDataInicial(LocalDate dataInicial) {
        return getResumoFinanceiro(dataInicial, LocalDate.now());
    }

    public Optional<ResumoFinanceiro> getResumoFinanceiroDataFinal(LocalDate dataFinal) {
        return getResumoFinanceiro(LocalDate.of(1970, 1, 1), dataFinal);
    }
}
