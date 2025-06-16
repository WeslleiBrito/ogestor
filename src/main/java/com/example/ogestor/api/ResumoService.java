package com.example.ogestor.api;

import com.example.ogestor.DTO.RetornoVendaItemDTO;
import com.example.ogestor.model.ResumoFinanceiro;
import com.example.ogestor.model.RetornoFaturamento;
import com.example.ogestor.model.RetornoTotalFaturamento;
import com.example.ogestor.model.RetornoVendaItem;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResumoService extends BaseAPI {

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

    public Optional<List<RetornoVendaItem>> getVendaItem(LocalDate dataInicial, LocalDate dataFinal) {
        try {
            String url = String.format(
                    "%svendas/venda-item?data_inicial=%s&data_final=%s",
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
                Type tipoLista = new TypeToken<List<RetornoVendaItemDTO>>() {}.getType();
                List<RetornoVendaItemDTO> dtoList = gson.fromJson(response.body(), tipoLista);

                List<RetornoVendaItem> lista = dtoList.stream()
                        .map(RetornoVendaItem::fromDTO)
                        .collect(Collectors.toList());

                return Optional.of(lista);
            } else {
                System.err.println("Erro: código " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao acessar API: " + e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<List<RetornoVendaItem>> getVendaItem() {

        return getVendaItem(LocalDate.now(), LocalDate.now());

    }

    public Optional<List<RetornoVendaItem>> getVendaItemDataInicial(LocalDate dataInicial) {

        return getVendaItem(dataInicial, LocalDate.now());

    }

    public Optional<List<RetornoVendaItem>> getVendaItemDataFinal(LocalDate dataFinal) {

        return getVendaItem(LocalDate.of(1970, 1, 1), dataFinal);

    }



}
