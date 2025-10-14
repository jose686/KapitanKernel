package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {
}