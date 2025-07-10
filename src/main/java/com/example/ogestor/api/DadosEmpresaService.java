package com.example.ogestor.api;

import com.example.ogestor.DTO.EmpresaDTO;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;


public class DadosEmpresaService extends BaseAPI{

    public Optional<EmpresaDTO> getEmpresa() {
        try {
            String url = baseUrl + "empresa";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                EmpresaDTO empresa = gson.fromJson(response.body(), EmpresaDTO.class);
                return Optional.of(empresa);
            } else {
                System.err.println("Erro: código " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao acessar API: " + e.getMessage());
        }

        return Optional.empty();
    }

}
