package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class Producto {

    @Id // 1. Anota el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 2. Indica que la BD genera el valor (opcional, pero común)
    private Long id;
    @Column(name = "sku_mayorista_unique", unique = true)
    private String skuMayoristaUnique;
    @Column(name = "id_mayorista_fk")
    private Long idMayoristaFk;
    @Column(name = "precio_decimal", precision = 10, scale = 2)
    private BigDecimal precioDecimal;
    @Column(name = "stock_integer")
    private Integer stockInteger;
    @Column(name = "id_imagen_fk")
    private Long idImagenFk;
    // Getters, Setters, Constructors
}