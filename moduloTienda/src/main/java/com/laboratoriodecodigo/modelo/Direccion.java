package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;
    @Column(name = "id_usuario_fk")
    private Long idUsuarioFk;
    @Enumerated(EnumType.STRING)
    private TipoDireccionEnum tipo; // ENUM: Envío, Facturación
    private String calle;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    private String ciudad;
    private String pais;

}