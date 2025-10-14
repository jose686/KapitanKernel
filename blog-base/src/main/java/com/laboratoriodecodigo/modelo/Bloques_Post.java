package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bloques_Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBloque;

    @ManyToOne
    @JoinColumn(name = "idPost", nullable = false)
    private Posts post;
    @Enumerated(EnumType.STRING)
    private TipoBloque tipoBloque;
    private long orden;
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "idImagen")
    private Imagenes imagen;



}
