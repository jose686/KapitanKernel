package com.laboratoriodecodigo.modelodto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GNewsResponse {

    private int totalArticles;
    private List<ArticleDTO> articles; // <-- Lista de los artículos

}