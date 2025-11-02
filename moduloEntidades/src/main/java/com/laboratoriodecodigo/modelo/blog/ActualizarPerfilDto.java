package com.laboratoriodecodigo.modelo.blog;

// Archivo: com.laboratoriodecodigo.dto.ActualizarPerfilDto.java



public class ActualizarPerfilDto {
    private String nuevoNombre;
    private String nuevoCorreo;

    // Getters
    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public String getNuevoCorreo() {
        return nuevoCorreo;
    }

    // Setters
    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public void setNuevoCorreo(String nuevoCorreo) {
        this.nuevoCorreo = nuevoCorreo;
    }
}