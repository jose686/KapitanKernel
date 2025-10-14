package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}