package com.laboratoriodecodigo.modelo;


import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table (name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @Column(unique = true)
    private String nombreUsuario;
    @Column(unique = true)
    private String correo;
    @Column(nullable = false)
    private String contrasena_hash;


    @Column(nullable = false)
    private LocalDateTime fecharegistro;

    @ManyToOne
    @JoinColumn(name = "tipoUsuario", nullable = false)
    private TiposUsuario tipoUsuario;



}
