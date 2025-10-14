package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analisis_ia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalisisIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIaAnalisis;

    @Lob
    @Column(name = "prompt_text")
    private String promptText;

    @Lob
    @Column(name = "respuesta_ia_text")
    private String respuestaIaText;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_analisis_enum")
    private TipoAnalisisEnum tipoAnalisis;

    @Column(name = "fecha_analisis_datetime", columnDefinition = "DATETIME")
    private LocalDateTime fechaAnalisisDatetime;

    @Column(name = "coste_token_integer")
    private Integer costeTokenInteger;


}