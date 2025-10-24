package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.iaContenido.AnalisisIA;

import java.util.Optional;

public interface IIAService {

    AnalisisIA analizarContenido(Long entidadId, String tipoEntidad, String contenidoATratar);

    // 2. Generar texto de marketing/SEO usando la IA (ej. para ProductoDetalle)
    String generarContenidoMarketing(String tema, String contexto);

    // 3. Obtener el análisis guardado de una entidad específica
    Optional<AnalisisIA> findLatestAnalysis(Long entidadId, String tipoEntidad);

    // 4. Guardar un resultado de análisis en la base de datos
    AnalisisIA save(AnalisisIA analisis);

}