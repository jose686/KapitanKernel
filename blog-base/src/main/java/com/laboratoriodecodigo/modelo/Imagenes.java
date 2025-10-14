package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imagenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idImagen;
    private String nombreArchivo;
    private String ruta;
    private String altText;
    private Date fechaSubida;


    @ManyToOne
    @JoinColumn(name = "idUsuarioSubida", nullable = false)
    private Usuario usuarioSubida;


}
