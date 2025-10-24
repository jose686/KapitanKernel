package com.laboratoriodecodigo.modelo.analitica;

public enum TipoEventoEnum {
    LOGIN_EXITOSO,
    LOGOUT,

    // Eventos de la tienda online (custom computers [cite: 2025-08-22])
    PRODUCTO_VISTO,
    CARRITO_AGREGADO,
    CHECKOUT_INICIADO,
    COMPRA_FINALIZADA, // Un evento clave para las finanzas

    // Eventos del blog/contenido (parte del módulo base)
    ARTICULO_LEIDO,
    COMENTARIO_PUBLICADO,

    // Eventos de gestión de la IA
    CONSULTA_IA
}
