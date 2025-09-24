package com.laboratoriodecodigo.KapitanKernel.modelo;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoria;
    private String nombreCategoria;

    @ManyToMany(mappedBy = "categorias")
    private Set<Posts> posts;

    public Categorias() {

    }

    public Categorias(String nombreCategoria, Set<Posts> posts) {
        this.nombreCategoria = nombreCategoria;
        this.posts = posts;
    }

    public Categorias(long idCategoria, String nombreCategoria, Set<Posts> posts) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.posts = posts;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Set<Posts> getPosts() {
        return posts;
    }

    public void setPosts(Set<Posts> posts) {
        this.posts = posts;
    }
}
