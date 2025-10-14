package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "noticias_externas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticiaExterna {

    @Id
    @Column(name = "id_noticia", length = 32)
    private String idNoticia; // Usamos el ID de la API como PK

    private String titulo; // VARCHAR(255)
    private String descripcion; // VARCHAR(512)

    @Lob // Para grandes volúmenes de texto
    @Column(name = "contenido_raw")
    private String contenidoRaw; // TEXT (Crucial para alimentar a la IA)

    private String url; // VARCHAR(512)
    @Column(name = "url_imagen", length = 512)
    private String urlImagen;

    @Column(name = "publicado_en", columnDefinition = "DATETIME")
    private LocalDateTime publicadoEn;
    private String idioma; // VARCHAR(10)

    @Column(name = "nombre_fuente", length = 100)
    private String nombreFuente;

    @Column(name = "fecha_importacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaImportacion; // Usar @CreationTimestamp o CURRENT_TIMESTAMP en DDL

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_procesamiento")
    private EstadoProcesamientoEnum estadoProcesamiento; // ENUM

    @Column(name = "id_ia_analisis_fk")
    private Long idIaAnalisisFk; // FK a AnalisisIA

    // Getters, Setters, Constructors
    // Se asume la existencia del Enum EstadoProcesamientoEnum
}