package com.laboratoriodecodigo.modelo.analitica;



import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "web_actividad_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebActividadUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;

    @Column(name = "id_usuario_fk")
    private Long idUsuarioFk; // Puede ser NULL

    @Column(name = "sesion_id")
    private String sesionId;

    @Column(name = "fecha_hora", columnDefinition = "DATETIME")
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento")
    private TipoEventoEnum tipoEvento; // ENUM: VISITA, CLICK, BUSQUEDA, COMPRA

    @Column(name = "url_pagina")
    private String urlPagina;

    @Column(name = "referencia_contexto")
    private String referenciaContexto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_fk")
    private Usuario usuario;

}