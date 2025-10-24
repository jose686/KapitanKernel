package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.tienda.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}