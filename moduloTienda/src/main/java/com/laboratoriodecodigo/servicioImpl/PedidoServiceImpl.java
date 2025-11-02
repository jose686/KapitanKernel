package com.laboratoriodecodigo.servicioImpl;


import com.laboratoriodecodigo.modelo.tienda.*;
import com.laboratoriodecodigo.servicios.IPedidoService;
import com.laboratoriodecodigo.servicios.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoServiceImpl implements IPedidoService {


    private final List<Pedido> pedidosDB = new ArrayList<>();
    private final AtomicLong nextPedidoId = new AtomicLong(1L);


    private final IProductoService productoService;
    @Autowired
    public PedidoServiceImpl(IProductoService productoService ) {
        this.productoService = productoService;
    }



    @Override
    public Pedido iniciarPedido(Long idUsuario, Long idDireccionEnvio, List<Long> listaIdProductos) {

        Pedido nuevoPedido = Pedido.builder()
                .idPedido(nextPedidoId.getAndIncrement())
                .fechaPedidoDatetime(LocalDateTime.now())
                .estado(EstadoPedidoEnum.PENDIENTE_PAGO) // Estado inicial
                .totalDecimal(BigDecimal.ZERO)
                .detallesPedido(new ArrayList<>())
                .build();

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Long idProducto : listaIdProductos) {
            Optional<Producto> productoOpt = productoService.buscarPorId(idProducto);

            if (productoOpt.isPresent() && productoOpt.get().getStockInteger() > 0) {
                Producto p = productoOpt.get();


                DetallePedido detalle = DetallePedido.builder()
                        .pedido(nuevoPedido)
                        .producto(p)
                        .cantidadInteger(1)
                        .precioUnitarioDecimal(p.getPrecioPvpSugerido())
                        .costoUnidadDecimal(p.getPrecioCostoDecimal())
                        .build();

                nuevoPedido.getDetallesPedido().add(detalle);
                subtotal = subtotal.add(p.getPrecioPvpSugerido());

            }
        }

        nuevoPedido.setTotalDecimal(subtotal);
        pedidosDB.add(nuevoPedido);
        System.out.println("🛒 PEDIDO #" + nuevoPedido.getIdPedido() + " INICIADO. Total: " + nuevoPedido.getTotalDecimal() + "€");
        return nuevoPedido;
    }

    @Override
    public Optional<Pedido> buscarPorId(Long idPedido) {
        return pedidosDB.stream().filter(p -> p.getIdPedido().equals(idPedido)).findFirst();
    }

    @Override
    public List<Pedido> buscarPorEstado(EstadoPedidoEnum estado) {
        return pedidosDB.stream().filter(p -> p.getEstado().equals(estado)).toList();
    }

    @Override
    public Pedido actualizarEstado(Long idPedido, EstadoPedidoEnum nuevoEstado) {
        Pedido pedido = buscarPorId(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);
        System.out.println("🔄 PEDIDO #" + idPedido + " -> Nuevo Estado: " + nuevoEstado);
        return pedido;
    }

    @Override
    public Pedido confirmarPago(Long idPedido, String transaccionId) {
        Pedido pedido = actualizarEstado(idPedido, EstadoPedidoEnum.PAGO_CONFIRMADO);
        pedido.setMetodoPago(MetodoPagoEnum.STRIPE); // Simulación
        pedido.setTransaccionIdUnique(transaccionId);
        System.out.println("💰 PAGO CONFIRMADO para PEDIDO #" + idPedido + ". ID Transacción: " + transaccionId);

        return pedido;
    }

    @Override
    public BigDecimal calcularTotal(Long idPedido) {
        return buscarPorId(idPedido)
                .map(Pedido::getTotalDecimal)
                .orElse(BigDecimal.ZERO);
    }
}