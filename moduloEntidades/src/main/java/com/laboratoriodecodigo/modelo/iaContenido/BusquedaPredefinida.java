package com.laboratoriodecodigo.modelo.iaContenido;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "busquedas_predefinidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusquedaPredefinida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_busqueda")
    private Long idBusqueda;

    @Column(name = "palabras_clave", length = 255, nullable = false)
    private String palabrasClave; // Ej: "gaming, consolas, esports"

    @Column(name = "limite_noticias")
    private Integer limiteNoticias; // Máximo a obtener

    @Column(name = "activa")
    private Boolean activa = true; // Si el Cron debe ejecutarla

    @Column(name = "frecuencia_minutos")
    private Integer frecuenciaMinutos; // Periodicidad (ej: 60 para cada hora)

    @Column(name = "ultima_ejecucion", columnDefinition = "DATETIME")
    @CreationTimestamp
    private LocalDateTime ultimaEjecucion;

    // Relación Muchos a Uno con Etiqueta: Una búsqueda asigna UNA etiqueta.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etiqueta_fk", nullable = false)
    private Etiqueta etiqueta;
}