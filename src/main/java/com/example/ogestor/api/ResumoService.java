package com.example.ogestor.api;

import com.example.ogestor.model.ResumoFinanceiro;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResumoService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseUrl = "http://127.0.0.1:8000/";
    private final Gson gson = new Gson();

    public ResumoFinanceiro getResumoFinanceiro () throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "resumo-financeiro/"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), ResumoFinanceiro.class);
    }


}
