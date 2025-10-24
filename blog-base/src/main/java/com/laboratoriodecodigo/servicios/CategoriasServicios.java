package com.laboratoriodecodigo.servicios;





import com.laboratoriodecodigo.modelo.blog.Categorias;

import java.util.List;
import java.util.Optional;

public interface CategoriasServicios {


    Categorias guardarCategoria(Categorias categoria);

    void eliminarCategoria(Long id);

    Optional<Categorias> obtenerCategoriaPorId(Long id);

    Categorias obtenerCategoriaPorNombre(String nombre);

    List<Categorias> listarCategorias();
}
