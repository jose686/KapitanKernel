package com.laboratoriodecodigo.modelo.tienda;

public enum EstadoPedidoEnum {
    // 1. Estados iniciales (antes de ser procesado)
    PENDIENTE_PAGO,
    PAGO_CONFIRMADO,

    // 2. Estados de preparación y envío
    EN_PREPARACION,
    ENVIADO,
    EN_TRANSITO,

    // 3. Estados finales
    ENTREGADO,

    // 4. Estados de anulación
    CANCELADO,
    REEMBOLSADO
}
