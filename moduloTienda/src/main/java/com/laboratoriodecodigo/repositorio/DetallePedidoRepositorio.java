package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.tienda.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {
}