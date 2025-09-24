package com.laboratoriodecodigo.KapitanKernel.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "tipos_usuarios")
public class TiposUsuario {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;
    @Column(nullable = false, unique = true)
    private String nombreTipo;





    public TiposUsuario() {
    }

    public TiposUsuario(long idTipo, String nombreTipo) {
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
    }


    public TiposUsuario(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }


    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}
