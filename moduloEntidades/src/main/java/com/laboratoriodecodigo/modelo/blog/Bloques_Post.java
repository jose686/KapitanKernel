package com.laboratoriodecodigo.modelo.blog;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idPost", nullable = false)
    private Posts post;
    @Enumerated(EnumType.STRING)
    private TipoBloque tipoBloque;
    private long orden;
    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "idImagen")
    private Imagenes imagen;



}
