package com.laboratoriodecodigo.servicios;


import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;

import java.util.List;

public interface BusquedaPredefinidaService {

    /**
     * Guarda una nueva configuración de búsqueda o actualiza una existente.
     * @param busqueda La configuración a guardar.
     * @return La configuración guardada.
     */
    BusquedaPredefinida guardarBusqueda(BusquedaPredefinida busqueda);

    /**
     * Obtiene la lista de todas las búsquedas que deben ser ejecutadas por el Cron Job.
     * @return Lista de búsquedas activas.
     */
    List<BusquedaPredefinida> obtenerBusquedasActivas();

    /**
     * Obtiene todas las configuraciones de búsqueda (activas e inactivas).
     * @return Lista de todas las configuraciones.
     */
    List<BusquedaPredefinida> obtenerTodas();

    /**
     * Marca una búsqueda como inactiva (desactiva el Cron para ella).
     * @param idBusqueda ID de la búsqueda.
     */
    void desactivarBusqueda(Long idBusqueda);

    /**
     * Actualiza el campo ultimaEjecucion tras una sincronización exitosa.
     * @param busqueda La entidad a actualizar.
     */
    void actualizarUltimaEjecucion(BusquedaPredefinida busqueda);

    void eliminarBusqueda(Long idBusqueda);
}