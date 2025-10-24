package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.analitica.WebActividadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebActividadUsuarioRepositorio extends JpaRepository<WebActividadUsuario, Long> {
    // Métodos personalizados aquí, si fueran necesarios
}