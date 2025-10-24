package com.laboratoriodecodigo.modelodto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class SourceDTO {

    private String name; // Mapea al campo "name" dentro del objeto "source" del JSON
    private String url;  // Mapea al campo "url" de la fuente (opcional, si GNews lo incluye)

}