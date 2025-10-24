package com.laboratoriodecodigo.modelo.blog;

import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


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
    @CreationTimestamp
    private LocalDateTime fechaSubida;


    @ManyToOne
    @JoinColumn(name = "idUsuarioSubida", nullable = false)
    private Usuario usuarioSubida;


}

