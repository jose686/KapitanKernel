package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.Pedido;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> findAll();

    // 2. Obtener un pedido por su ID
    Optional<Pedido> findById(Long id);

    // 3. Crear un nuevo pedido (al finalizar la compra)
    Pedido createPedido(Pedido pedido, Long userId);

    // 4. Obtener pedidos por cliente
    List<Pedido> findByUsuario(Usuario usuario);

    // 5. Actualizar el estado del pedido (Pendiente, Enviado, Entregado)
    Pedido updateEstado(Long pedidoId, String nuevoEstado);
}
