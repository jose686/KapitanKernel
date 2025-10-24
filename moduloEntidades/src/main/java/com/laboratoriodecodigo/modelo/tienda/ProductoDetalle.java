package com.laboratoriodecodigo.modelo.tienda;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "producto_detalle")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class ProductoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELACIÓN OneToOne con Producto
    // Este será la Clave Foránea (FK) y al mismo tiempo el ID de este detalle
    // JoinColumn indica la columna en esta tabla que apunta a la tabla Producto
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // CAMPO RELLENO DE 500 PALABRAS (Descripción Larga para SEO)
    @Lob // Para almacenar textos largos
    @Column(name = "descripcion_larga_html", columnDefinition = "TEXT")
    private String descripcionLargaHtml;

    // Título optimizado para el navegador y Google
    @Column(name = "metatitulo_seo", length = 100)
    private String metatituloSeo;

    // Puntos clave de venta (ej. lista de iconos)
    @Lob
    @Column(name = "puntos_clave_venta_json", columnDefinition = "TEXT")
    private String puntosClaveVentaJson;

    // La descripción técnica corta que viene del mayorista para complementar la tuya
    @Lob
    @Column(name = "descripcion_tecnica_mayorista", columnDefinition = "TEXT")
    private String descripcionTecnicaMayorista;


}