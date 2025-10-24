package com.laboratoriodecodigo.modelo.usuarios;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laboratoriodecodigo.modelo.analitica.WebActividadUsuario;
import com.laboratoriodecodigo.modelo.tienda.Direccion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


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
    @CreationTimestamp
    private LocalDateTime fecharegistro;

    @ManyToOne
    @JoinColumn(name = "tipoUsuario", nullable = false)
    private TiposUsuario tipoUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WebActividadUsuario> historialActividad = new ArrayList<>();

}
