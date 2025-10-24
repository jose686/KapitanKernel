package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.finanzas.GastoTienda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IGastoService {

    GastoTienda save(GastoTienda gasto);

    Optional<GastoTienda> findById(Long id);

    List<GastoTienda> findByTipoGasto(String tipoGasto);

    // Consulta clave: Obtener el total de gastos en un rango de fechas
    BigDecimal calculateTotalGastos(LocalDateTime start, LocalDateTime end);
}
