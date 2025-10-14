package com.laboratoriodecodigo.KapitanKernel.modelo;


import java.time.LocalDateTime;


import jakarta.persistence.*;





@Entity
@Table (name = "usuarios")
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
    private LocalDateTime fecharegistro;

    @ManyToOne
    @JoinColumn(name = "tipoUsuario", nullable = false)
    private TiposUsuario tipoUsuario;


    public Usuario() {
    }

    public Usuario(String nombreUsuario, String correo, String contrasena_hash, TiposUsuario tipoUsuario,
                   LocalDateTime  fecharegistro) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena_hash = contrasena_hash;
        this.tipoUsuario = tipoUsuario;
        this.fecharegistro = fecharegistro;
    }

    public Usuario(long idUsuario, String nombreUsuario, String correo, String contrasena_hash, TiposUsuario tipoUsuario, LocalDateTime  fecharegistro) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena_hash = contrasena_hash;
        this.tipoUsuario = tipoUsuario;
        this.fecharegistro = fecharegistro;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena_hash() {
        return contrasena_hash;
    }

    public void setContrasena_hash(String contrasena_hash) {
        this.contrasena_hash = contrasena_hash;
    }

    public LocalDateTime  getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(LocalDateTime  fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public TiposUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TiposUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @PrePersist
    public void prePersist() {
        if (this.fecharegistro == null) {
            this.fecharegistro = LocalDateTime.now();
        }
    }

}
