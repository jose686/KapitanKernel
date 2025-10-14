package com.laboratoriodecodigo.modelo;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingresos_tienda")
public class IngresoTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngreso;

    @Column(name = "id_pedido_fk")
    private Long idPedidoFk;

    @Column(name = "fecha_ingreso", columnDefinition = "DATETIME")
    private LocalDateTime fechaIngreso;

    @Column(name = "monto_decimal", precision = 10, scale = 2)
    private BigDecimal montoDecimal;

    @Enumerated(EnumType.STRING)
    private ConceptoIngresoEnum concepto; // ENUM: VENTA_PRODUCTO, ENVIO

    @Column(name = "transaccion_id_fk")
    private String transaccionIdFk; // FK a la tabla de Transacciones (de Ecom-Tienda)

    // Getters, Setters, Constructors
    // Se asume la existencia del Enum ConceptoIngresoEnum
}
