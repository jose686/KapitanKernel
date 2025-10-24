package com.laboratoriodecodigo.modelo.finanzas;


import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos_tienda")
public class GastoTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGasto;

    @Column(name = "fecha_gasto", columnDefinition = "DATETIME")
    private LocalDateTime fechaGasto;

    @Column(name = "monto_decimal", precision = 10, scale = 2)
    private BigDecimal montoDecimal;

    @Enumerated(EnumType.STRING)
    private ConceptoGastoEnum concepto; // ENUM: ENVIO, MAYORISTA, PUBLICIDAD, IMPUESTOS

    @Column(name = "referencia")
    private String referencia; // Ej: Factura ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mayorista_fk") // Columna FK para el proveedor
    private Mayorista mayorista;
}