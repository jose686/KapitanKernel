package com.laboratoriodecodigo.modelo.tienda;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos_staging")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoStaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStaging; // ID interno de la tabla temporal

    @Column(name = "id_mayorista_fk", nullable = false)
    private Long idMayoristaFk;

    // Campos del Mayorista (datos crudos, sin margen aplicado)
    // --------------------------------------------------------
    @Column(name = "nombre_producto", length = 255)
    private String nombreProducto;

    @Column(name = "precio_costo_decimal", precision = 10, scale = 2)
    private BigDecimal precioCostoDecimal;

    @Column(name = "stock_integer")
    private Integer stockInteger;

    @Column(name = "sku_mayorista_unique", unique = true, length = 100)
    private String skuMayoristaUnique; // Clave de unión con la tabla Producto

    @Column(name = "url_imagen_principal", length = 512)
    private String urlImagenPrincipal;

    // Campos de Control y Lógica (para la curación)
    // --------------------------------------------------------

    // Indica si este producto ya existe en la tabla de producción 'Producto'
    @Column(name = "ya_existe_en_produccion")
    private Boolean yaExisteEnProduccion;

    // Marca si el producto cumple con los filtros (ej: Marca Asus/Nvidia)
    @Column(name = "aprobado_por_filtro")
    private Boolean aprobadoPorFiltro;

    @Column(name = "fecha_descarga_datetime", nullable = false)
    private LocalDateTime fechaDescargaDatetime;

    // Campo que puede usarse para guardar el ID de la tabla Producto si ya existe
    @Column(name = "id_producto_produccion_fk")
    private Long idProductoProduccionFk;
}