package com.laboratoriodecodigo.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table (name = "tipos_usuarios")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class TiposUsuario {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;
    @Column(nullable = false, unique = true)
    private String nombreTipo;



}
