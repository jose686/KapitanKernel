package com.laboratoriodecodigo.modelo;

public enum MetodoPagoEnum {
    // Métodos comunes de pasarela
    TARJETA_CREDITO,
    PAYPAL,
    STRIPE, // O la pasarela que uses

    // Métodos alternativos
    TRANSFERENCIA_BANCARIA,
    PAGO_CONTRA_REEMBOLSO // Si es aplicable
}
