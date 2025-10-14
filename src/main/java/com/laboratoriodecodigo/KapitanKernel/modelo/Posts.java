package com.laboratoriodecodigo.KapitanKernel.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;
    @ManyToOne
    private Usuario idAutor;


    @ManyToMany
    @JoinTable(
            name = "posts_categorias",
            joinColumns = @JoinColumn(name = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    private Set<Categorias> categorias;
    private String titulo;
    private String metaDescripcion;
    private Date fechaPublicacion;
    @Enumerated(EnumType.STRING)
    private PostStatus estado;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bloques_Post> bloquesDeContenido;

    public Posts() {
    }


    public Posts(long idTipo, Usuario idAutor, Set<Categorias> categoria, String titulo, String metaDescripcion, Date fechaPublicacion, PostStatus estado, List<Bloques_Post> bloquesDeContenido) {
        this.idTipo = idTipo;
        this.idAutor = idAutor;
        this.categorias = categoria;
        this.titulo = titulo;
        this.metaDescripcion = metaDescripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.bloquesDeContenido = bloquesDeContenido;
    }


    public Posts(Usuario idAutor, Set<Categorias> categoria, String titulo, String metaDescripcion, Date fechaPublicacion, PostStatus estado, List<Bloques_Post> bloquesDeContenido) {
        this.idAutor = idAutor;
        this.categorias = categoria;
        this.titulo = titulo;
        this.metaDescripcion = metaDescripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.bloquesDeContenido = bloquesDeContenido;
    }

    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    public Usuario getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Usuario idAutor) {
        this.idAutor = idAutor;
    }

    public Set<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categorias> categorias) {
        this.categorias = categorias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMetaDescripcion() {
        return metaDescripcion;
    }

    public void setMetaDescripcion(String metaDescripcion) {
        this.metaDescripcion = metaDescripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public PostStatus getEstado() {
        return estado;
    }

    public void setEstado(PostStatus estado) {
        this.estado = estado;
    }

    public List<Bloques_Post> getBloquesDeContenido() {
        return bloquesDeContenido;
    }

    public void setBloquesDeContenido(List<Bloques_Post> bloquesDeContenido) {
        this.bloquesDeContenido = bloquesDeContenido;
    }
}
