package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.tienda.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    Optional<Producto> findBySkuMayoristaUnique(String skuMayorista);
}