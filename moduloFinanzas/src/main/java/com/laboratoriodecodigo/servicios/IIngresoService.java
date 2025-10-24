package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.finanzas.IngresoTienda;
import com.laboratoriodecodigo.modelo.tienda.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IIngresoService {
    IngresoTienda save(IngresoTienda ingreso);

    Optional<IngresoTienda> findById(Long id);

    Optional<IngresoTienda> findByPedido(Pedido pedido); // Buscar el ingreso de un pedido específico

    List<IngresoTienda> findByTipoIngreso(String tipoIngreso);

    // Consulta clave: Obtener el total de ingresos en un rango de fechas
    BigDecimal calculateTotalIngresos(LocalDateTime start, LocalDateTime end);
}
