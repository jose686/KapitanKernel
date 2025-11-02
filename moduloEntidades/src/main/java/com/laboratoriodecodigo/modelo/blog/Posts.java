package com.laboratoriodecodigo.modelo.blog;

import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

@Entity
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;
    @ManyToOne
    private Usuario idAutor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_imagen_destacada_fk", referencedColumnName = "idImagen")
    private Imagenes imagenDestacada;
    private String imagenDestacadaUrl;

    @ManyToMany
    @JoinTable(
            name = "posts_categorias",
            joinColumns = @JoinColumn(name = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    private Set<Categorias> categorias;

    private String titulo;
    private String metaDescripcion;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaPublicacion;

    @Enumerated(EnumType.STRING)
    private PostStatus estado;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bloques_Post> bloquesDeContenido;

    @Column(name = "id_ia_generacion_fk")
    private Long idIaGeneracionFk;
}