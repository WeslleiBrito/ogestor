package com.example.ogestor.api;

import com.example.ogestor.model.RetornoTotalFaturamento;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;

public class ResumoTotalFaturamentoService extends BaseAPI{

    public Optional<RetornoTotalFaturamento> getResumoTotalFaturamento(LocalDate dataInicial, LocalDate dataFinal) {

        try {

            String url = String.format(
                    "%svendas/resumo-total-venda?data_inicial=%s&data_final=%s",
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
                                RetornoTotalFaturamento.class
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

    public Optional<RetornoTotalFaturamento> getResumoTotalFaturamento() {
        return getResumoTotalFaturamento(LocalDate.now(), LocalDate.now());
    }

    public Optional<RetornoTotalFaturamento> getResumoTotalFaturamentoDataIncial(LocalDate dataInicial) {
        return getResumoTotalFaturamento(dataInicial, LocalDate.now());
    }

    public Optional<RetornoTotalFaturamento> getResumoTotalFaturamentoDataFinal(LocalDate dataFinal) {
        return getResumoTotalFaturamento(LocalDate.of(1970, 1, 1), dataFinal);
    }
}
