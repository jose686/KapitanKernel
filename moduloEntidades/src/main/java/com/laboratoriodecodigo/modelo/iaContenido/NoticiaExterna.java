package com.laboratoriodecodigo.modelo.iaContenido;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "noticias_externas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticiaExterna {

    @Id
    @Column(name = "id_noticia", length = 255)
    private String idNoticia;

    // Mapeo: Atributo 'title' del DTO se guarda en la columna 'titulo' de la DB
    @Column(name = "titulo")
    private String title; // Antes: titulo

    // Mapeo: Atributo 'description' del DTO se guarda en la columna 'descripcion' de la DB
    @Column(name = "descripcion", length = 1024)
    private String description; // Antes: descripcion

    // Mapeo: Atributo 'content' del DTO se guarda en la columna 'contenido_raw' de la DB
    @Column(name = "contenido_raw", columnDefinition = "LONGTEXT")
    private String content; // Antes: contenidoRaw (Crucial para alimentar a la IA)

    private String url;

    // Mapeo: Atributo 'image' del DTO se guarda en la columna 'url_imagen' de la DB
    @Column(name = "url_imagen", length = 512)
    private String image; // Antes: urlImagen

    // Mapeo: Atributo 'publishedAt' (convertido) se guarda en la columna 'publicado_en' de la DB
    @Column(name = "publicado_en", columnDefinition = "DATETIME")
    private LocalDateTime publishedAt; // Antes: publicadoEn (Usaremos este nombre para la fecha convertida)

    private String idioma;

    // Mapeo: Usaremos 'sourceName' para el nombre de la fuente
    @Column(name = "nombre_fuente", length = 100)
    private String sourceName; // Antes: nombreFuente

    @Column(name = "fecha_importacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaImportacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_procesamiento", length = 30)
    private EstadoProcesamientoEnum estadoProcesamiento;

    @Column(name = "id_ia_analisis_fk")
    private Long idIaAnalisisFk;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "noticia_etiqueta", // Nombre de la tabla intermedia que Hibernate creará
            joinColumns = @JoinColumn(name = "id_noticia_fk"), // Columna que se une a NoticiaExterna
            inverseJoinColumns = @JoinColumn(name = "id_etiqueta_fk") // Columna que se une a Etiqueta
    )
    private Set<Etiqueta> etiquetas;

}