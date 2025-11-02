package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.EstadoPedidoEnum;
import com.laboratoriodecodigo.modelo.tienda.Pedido;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    // 1. Crear un pedido (inicialmente en estado PENDIENTE_PAGO)
    Pedido iniciarPedido(Long idUsuario, Long idDireccionEnvio, List<Long> listaIdProductos);

    // 2. Buscar un pedido por su ID
    Optional<Pedido> buscarPorId(Long idPedido);

    // 3. Buscar todos los pedidos por un estado específico (ej. EN_PREPARACION)
    List<Pedido> buscarPorEstado(EstadoPedidoEnum estado);

    // 4. Actualizar el estado del pedido (ej. de PAGO_CONFIRMADO a EN_PREPARACION)
    Pedido actualizarEstado(Long idPedido, EstadoPedidoEnum nuevoEstado);

    // 5. Finalizar la transacción y confirmar el pago (Guarda la transacción ID)
    Pedido confirmarPago(Long idPedido, String transaccionId);

    // 6. Calcular el total del carrito antes de finalizar la compra
    BigDecimal calcularTotal(Long idPedido);
}
