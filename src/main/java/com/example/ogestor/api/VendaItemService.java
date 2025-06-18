package com.example.ogestor.api;

import com.example.ogestor.DTO.RetornoVendaItemDTO;
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

public class VendaItemService extends BaseAPI{

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
