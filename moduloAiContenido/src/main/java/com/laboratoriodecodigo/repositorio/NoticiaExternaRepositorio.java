package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.NoticiaExterna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaExternaRepositorio extends JpaRepository<NoticiaExterna, String> {
    // La clave primaria de NoticiaExterna es un String (el ID de la API)
}
