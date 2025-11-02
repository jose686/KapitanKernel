package com.laboratoriodecodigo.modelo.tienda;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad para guardar el contenido enriquecido (descripción, SEO, marketing)
 * que no viene del mayorista, sino que se añade internamente.
 */
@Entity
@Table(name = "descripciones_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescripcionProducto {

    // 1. Clave Primaria compartida con Producto (CRÍTICO para 1:1)
    @Id
    @Column(name = "id_producto_fk")
    private Long idProductoFk;

    // 2. Contenido Enriquecido
    @Lob // Permite almacenar grandes cantidades de texto (TEXT/VARCHAR(MAX) en MySQL)
    @Column(name = "descripcion_larga", nullable = false)
    private String descripcionLarga;

    @Column(name = "resumen_seo", length = 300)
    private String resumenSeo;

    @Lob
    @Column(name = "contenido_marketing")
    private String contenidoMarketing;

    @Column(name = "ultima_modificacion_ia")
    private LocalDateTime ultimaModificacionIA;

    // 3. Relación Uno a Uno Inversa (Dueña de la Relación)
    // ----------------------------------------------------------------------
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // ¡IMPORTANTE! Indica que el PK (idProductoFk) se mapea al FK
    @JoinColumn(name = "id_producto_fk")
    private Producto producto;

}