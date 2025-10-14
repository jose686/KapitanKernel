package com.laboratoriodecodigo.modelo;

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
    @Column(name = "id_pedido_fk")
    private Long idPedidoFk;
    @Column(name = "id_producto_fk")
    private Long idProductoFk;
    @Column(name = "cantidad_integer")
    private Integer cantidadInteger;
    @Column(name = "precio_unitario_decimal", precision = 10, scale = 2)
    private BigDecimal precioUnitarioDecimal;

}