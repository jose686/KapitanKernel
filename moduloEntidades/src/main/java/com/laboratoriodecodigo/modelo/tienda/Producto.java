package com.laboratoriodecodigo.modelo.tienda;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador único del mayorista
    @Column(name = "sku_mayorista_unique", unique = true, nullable = false)
    private String skuMayoristaUnique;

    // ID de la tabla Mayorista (si la tienes)
    @Column(name = "id_mayorista_fk")
    private Long idMayoristaFk;

    // NUEVOS CAMPOS DESDE EL JSON DEL MAYORISTA
    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "precio_costo_decimal", precision = 10, scale = 2)
    private BigDecimal precioCostoDecimal; // Precio al que lo compras

    @Column(name = "precio_pvp_sugerido", precision = 10, scale = 2)
    private BigDecimal precioPvpSugerido; // Precio al que se vende

    @Column(name = "stock_integer")
    private Integer stockInteger;

    @Column(name = "url_imagen_principal")
    private String urlImagenPrincipal; // Guardamos la URL para no sobrecargar la tabla de Imagen

    // RELACIÓN OneToOne con ProductoDetalle (Contenido SEO)
    // Usamos CascadeType.ALL para que si borras el producto, se borre su detalle.
    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductoDetalle detalle;

    // RELACIÓN INVERSA (Historial de Ventas)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetallePedido> historialVentas = new ArrayList<>();





}