package com.laboratoriodecodigo.modelo.tienda;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedido")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;
    @Column(name = "cantidad_integer")
    private Integer cantidadInteger;

    // ----------------------------------------------------------------------
    // RELACIÓN 1: Muchos DetallePedido a Un Pedido (@ManyToOne)
    // ----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido_fk", nullable = false)
    private Pedido pedido;

    // ----------------------------------------------------------------------
    // RELACIÓN 2: Muchos DetallePedido a Un Producto (@ManyToOne)
    // ----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_fk", nullable = false)
    private Producto producto;



    // Campo ya existente (Precio de Venta)
    @Column(name = "precio_unitario_decimal", precision = 10, scale = 2)
    private BigDecimal precioUnitarioDecimal;

    // ----------------------------------------------------------------------
    // CAMPO CRÍTICO PARA EL MARGEN DE BENEFICIO
    // ----------------------------------------------------------------------
    @Column(name = "costo_unidad_decimal", precision = 10, scale = 2)
    private BigDecimal costoUnidadDecimal;
}