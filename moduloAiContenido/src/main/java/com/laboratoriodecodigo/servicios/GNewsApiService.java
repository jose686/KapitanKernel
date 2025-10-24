package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelodto.ArticleDTO;

import java.util.List;

public interface GNewsApiService {

    /**
     * Llama a la API de GNews para buscar artículos.
     * @param query La palabra clave de búsqueda (ej: 'IA', 'tecnología').
     * @param limit El número máximo de artículos a solicitar.
     * @return Una lista de ArticleDTO mapeados desde la respuesta JSON.
     */
    List<ArticleDTO> buscarNoticias(String query, int limit);
}