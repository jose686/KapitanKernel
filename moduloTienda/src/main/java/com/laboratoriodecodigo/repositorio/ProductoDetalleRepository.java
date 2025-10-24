package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.tienda.ProductoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoDetalleRepository extends JpaRepository<ProductoDetalle, Long> {



    ProductoDetalle findByProductoId(Long productoId);
}