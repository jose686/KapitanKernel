package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.Imagenes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenesRepository extends JpaRepository<Imagenes,Long> {
}