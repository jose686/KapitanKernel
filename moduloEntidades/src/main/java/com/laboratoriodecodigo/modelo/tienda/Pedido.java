package com.laboratoriodecodigo.modelo.tienda;

import com.laboratoriodecodigo.modelo.finanzas.IngresoTienda;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // RELACIÓN 1: Dirección de Envío
    // ----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion_envio_fk", nullable = false)
    private Direccion direccionEnvio;

    // ----------------------------------------------------------------------
    // RELACIÓN 2: Dirección de Facturación
    // ----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion_facturacion_fk", nullable = false)
    private Direccion direccionFacturacion;

    // ----------------------------------------------------------------------
    // RELACIÓN 3: Con el Usuario (que implementamos antes)
    // ----------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_fk", nullable = false)
    private Usuario usuario;

    // RELACIÓN: Un Pedido a Muchos DetallePedido (@OneToMany)
    // ----------------------------------------------------------------------
    // mappedBy = "pedido" debe coincidir con el nombre del campo en DetallePedido.java
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallesPedido = new ArrayList<>();


    // RELACIÓN JPA: Un Pedido a Muchos Ingresos (para pagos parciales/ajustes)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<IngresoTienda> movimientosIngreso;
}