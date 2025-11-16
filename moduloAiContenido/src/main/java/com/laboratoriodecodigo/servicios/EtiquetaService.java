package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;

import java.util.List;

public interface EtiquetaService {

    /**
     * Guarda una nueva etiqueta o actualiza una existente.
     * @param etiqueta La entidad Etiqueta a guardar.
     * @return La etiqueta guardada.
     */
    Etiqueta guardarEtiqueta(Etiqueta etiqueta);

    /**
     * Busca una etiqueta por su ID.
     * @param idEtiqueta ID de la etiqueta.
     * @return La etiqueta si existe, o null.
     */
    Etiqueta buscarPorId(Long idEtiqueta);

    /**
     * Busca una etiqueta por su nombre (útil antes de crear nuevas).
     * @param nombre Nombre de la etiqueta.
     * @return La etiqueta si existe, o null.
     */
    Etiqueta buscarPorNombre(String nombre);

    /**
     * Obtiene todas las etiquetas existentes.
     * @return Lista de todas las etiquetas.
     */
    List<Etiqueta> obtenerTodas();

    boolean existePorNombre(String nombre);

    void eliminarEtiqueta(Long idEtiqueta);
}