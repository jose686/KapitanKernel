package com.laboratoriodecodigo.modelo.iaContenido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Set;

@Entity
@Table(name = "etiquetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private Long idEtiqueta;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre; // Ej: Videojuegos, IA, Economía

    @Column(name = "slug", length = 50, nullable = false, unique = true)
    private String slug;

    @JsonIgnore
    @ManyToMany(mappedBy = "etiquetas")
    private Set<NoticiaExterna> noticias;
}