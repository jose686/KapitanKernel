package com.laboratoriodecodigo.seviciosIpml;

import com.laboratoriodecodigo.modelodto.ArticleDTO;
import com.laboratoriodecodigo.servicios.GNewsApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.laboratoriodecodigo.modelodto.GNewsResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
public class GNewsApiServiceImpl implements GNewsApiService {

    // 💡 RestTemplate necesita ser configurado como Bean en una clase @Configuration (lo asumo).
    private final RestTemplate restTemplate;

    // Inyecta la clave desde application.properties (ej: gnews.api.key=...)
    @Value("${gnews.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://gnews.io/api/v4/";

    public GNewsApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ArticleDTO> buscarNoticias(String query, int limit) {

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "search")
                .queryParam("q", encodedQuery) // USAMOS EL VALOR CODIFICADO
                .queryParam("lang", "es")
                .queryParam("max", limit)
                .queryParam("token", apiKey)
                .build() // Ya no necesitamos .encode(), el valor de 'q' ya está codificado.
                .toUriString();

        try {
            // 2. Ejecutar la petición y mapear el JSON a nuestro DTO GNewsResponse
            GNewsResponse response = restTemplate.getForObject(url, GNewsResponse.class);

            // 3. Devolver la lista de artículos
            if (response != null && response.getArticles() != null) {
                return response.getArticles();
            }
        } catch (Exception e) {
            // Manejo de errores de conexión/JSON (puedes loggearlo mejor)
            System.err.println("Error al conectar con la API de GNews: " + e.getMessage());
        }

        return Collections.emptyList(); // Devuelve una lista vacía si falla
    }
}