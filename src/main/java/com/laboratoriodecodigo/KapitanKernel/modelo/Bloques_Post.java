package com.laboratoriodecodigo.KapitanKernel.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
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


    public Bloques_Post() {
    }

    public Bloques_Post(long idBloque, Posts post, String TipoBloque, long orden, String contenido, Imagenes imagen) {
        this.idBloque = idBloque;
        this.post = post;
        this.tipoBloque = tipoBloque;
        this.orden = orden;
        this.contenido = contenido;
        this.imagen = imagen;
    }

    public Bloques_Post(Posts post, String TipoBloque, long orden, String contenido, Imagenes imagen) {
        this.post = post;
        this.tipoBloque = tipoBloque;
        this.orden = orden;
        this.contenido = contenido;
        this.imagen = imagen;
    }

    public long getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(long idBloque) {
        this.idBloque = idBloque;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public TipoBloque getTipoBloque() {
        return tipoBloque;
    }

    public void setTipoBloque(TipoBloque tipoBloque) {
        this.tipoBloque = tipoBloque;
    }

    public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Imagenes getImagen() {
        return imagen;
    }

    public void setImagen(Imagenes imagen) {
        this.imagen = imagen;
    }
}
