package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}