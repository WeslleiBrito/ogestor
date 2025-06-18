package com.example.ogestor.api;

import com.example.ogestor.model.RetornoVendedor;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class VendedorService extends BaseAPI{

    public Optional<List<RetornoVendedor>> getVendasVendedor(LocalDate dataInicial, LocalDate dataFinal) {
        try {
            String url = String.format(
                    "%svendas/venda-vendedor?data_inicial=%s&data_final=%s",
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
                Type listType = new TypeToken<List<RetornoVendedor>>() {}.getType();
                List<RetornoVendedor> lista = gson.fromJson(response.body(), listType);
                return Optional.ofNullable(lista);
            } else {
                System.err.println("Erro: código " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao acessar API: " + e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<List<RetornoVendedor>> getVendasVendedor() {

        return getVendasVendedor(LocalDate.now(), LocalDate.now());

    }

    public Optional<List<RetornoVendedor>> getVendasVendedorDataInicial(LocalDate dataInicial) {

        return getVendasVendedor(dataInicial, LocalDate.now());

    }

    public Optional<List<RetornoVendedor>> getVendasVendedorDataFinal(LocalDate dataFinal) {

        return getVendasVendedor(LocalDate.of(1970, 1, 1), dataFinal);

    }
}
