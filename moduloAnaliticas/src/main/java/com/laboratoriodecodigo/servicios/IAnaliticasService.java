package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.analitica.WebActividadUsuario;

import java.time.LocalDateTime;
import java.util.List;

public interface IAnaliticasService {
    // 1. Guardar un evento de actividad del usuario
    WebActividadUsuario save(WebActividadUsuario actividad);

    // 2. Obtener el número de visitas (ej: 'PAGE_VIEW') por URL en un rango
    Long countVisitsByUrl(String url, LocalDateTime start, LocalDateTime end);

    // 3. Obtener el número de veces que un producto fue añadido al carrito
    Long countAddToCartByProductId(Long productId, LocalDateTime start, LocalDateTime end);

    // 4. Obtener las sesiones activas (usuarios únicos que han tenido actividad reciente)
    List<String> findActiveSessions(LocalDateTime lastActiveTime);

    // 5. Método de Reporting: Obtener los eventos de un tipo específico en un rango
    List<WebActividadUsuario> findEventsByTypeAndDateRange(String tipoEvento, LocalDateTime start, LocalDateTime end);
}