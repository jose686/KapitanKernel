package com.laboratoriodecodigo.KapitanKernel.modelo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
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

    public Imagenes() {
    }

    public Imagenes(String nombreArchivo, String ruta, String altText, Date fechaSubida, Usuario usuarioSubida) {
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.altText = altText;
        this.fechaSubida = fechaSubida;
        this.usuarioSubida = usuarioSubida;
    }

    public Imagenes(long idImagen, String nombreArchivo, String ruta, String altText, Date fechaSubida, Usuario usuarioSubida) {
        this.idImagen = idImagen;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.altText = altText;
        this.fechaSubida = fechaSubida;
        this.usuarioSubida = usuarioSubida;
    }

    public long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(long idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public Usuario getUsuarioSubida() {
        return usuarioSubida;
    }

    public void setUsuarioSubida(Usuario usuarioSubida) {
        this.usuarioSubida = usuarioSubida;
    }
}
