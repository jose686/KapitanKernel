package com.laboratoriodecodigo.KapitanKernel.servicios;

import com.laboratoriodecodigo.KapitanKernel.modelo.Categorias;

import java.util.List;
import java.util.Optional;

public interface CategoriasServicios {


    Categorias guardarCategoria(Categorias categoria);

    void eliminarCategoria(Long id);

    Optional<Categorias> obtenerCategoriaPorId(Long id);

    Categorias obtenerCategoriaPorNombre(String nombre);

    List<Categorias> listarCategorias();
}
