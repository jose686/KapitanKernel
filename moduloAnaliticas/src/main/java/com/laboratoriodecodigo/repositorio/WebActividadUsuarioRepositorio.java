package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.WebActividadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebActividadUsuarioRepositorio extends JpaRepository<WebActividadUsuario, Long> {
    // Métodos personalizados aquí, si fueran necesarios
}