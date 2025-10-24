package com.laboratoriodecodigo.modelodto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
    private String title;
    private String description;
    private String content; // GNews usa 'content', tu entidad usa 'contenidoRaw'
    private String url;
    private String image;
    private String publishedAt; // Se mapea como String, luego se convierte a LocalDateTime
    private SourceDTO source;
}