package com.laboratoriodecodigo.modelo.iaContenido;

public enum EstadoProcesamientoEnum {
    // 1. GESTIÓN Y CURACIÓN
    IMPORTADO,           // La noticia ha sido guardada en la DB (Estado inicial, similar a PENDIENTE)
    PENDIENTE,           // PENDIENTE de revisión o de iniciar el proceso de IA (Mantenemos por consistencia)
    APROBADO,            // La noticia ha sido revisada/completada y está lista para ser publicada.
    RECHAZADO,           // La noticia se descarta por falta de relevancia, etc.

    // 2. PASOS ESPECÍFICOS DE PROCESAMIENTO IA/CHATBOT
    RESUMEN_NOTICIA,     // La IA está trabajando en el resumen.
    ANALISIS_SENTIMIENTO,// La IA está analizando el sentimiento.
    REVISION_SEO,        // La IA está revisando/generando datos SEO.
    GENERACION_PRODUCTO, // Se está generando un producto relacionado (Tienda).
    CHATBOT_RESPUESTA,   // La noticia se usa para alimentar una respuesta de chatbot.

    // 3. FINALIZACIÓN
    COMPLETADO_IA
}
