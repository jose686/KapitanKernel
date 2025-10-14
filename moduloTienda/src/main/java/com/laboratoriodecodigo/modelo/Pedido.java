package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @Column(name = "id_usuario_fk")
    private Long idUsuarioFk;
    @Column(name = "fecha_pedido_datetime", columnDefinition = "DATETIME")
    private LocalDateTime fechaPedidoDatetime;
    @Enumerated(EnumType.STRING)
    private EstadoPedidoEnum estado; // ENUM: Pendiente, Enviado, Completado
    @Column(name = "total_decimal", precision = 10, scale = 2)
    private BigDecimal totalDecimal;
    @Column(name = "transaccion_id_unique", unique = true)
    private String transaccionIdUnique;
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago_enum")
    private MetodoPagoEnum metodoPago; // ENUM: Tarjeta, PayPal, Transferencia
    // Getters, Setters, Constructors
    // Se asume la existencia de Enums
}