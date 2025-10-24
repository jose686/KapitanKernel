package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.DetallePedido;
import com.laboratoriodecodigo.modelo.tienda.Pedido;

import java.util.List;

public interface IDetallePedidoService {// 1. Obtener los detalles de un pedido específico
    List<DetallePedido> findByPedido(Pedido pedido);

    // 2. Guardar una nueva línea de detalle
    DetallePedido save(DetallePedido detalle);

    // 3. Calcular el subtotal de un detalle de pedido
    double calculateSubtotal(DetallePedido detalle);
}
