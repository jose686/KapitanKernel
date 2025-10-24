package com.laboratoriodecodigo.servicios;


import com.laboratoriodecodigo.modelo.tienda.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// CONTRATO para manejar las operaciones del Producto Principal (Mayorista)
public interface IProductoService {

    // 1. Cargar el catálogo completo o paginado
    List<Producto> findAll();

    // 2. Buscar un producto por su ID de nuestra base de datos
    Optional<Producto> findById(Long id);

    // 3. Buscar un producto por su SKU de mayorista (clave única)
    Optional<Producto> findBySku(String skuMayorista);

    // 4. Guardar un producto nuevo o actualizar uno existente
    Producto save(Producto producto);

    // 5. Actualizar solo el precio y el stock (usado en la sincronización diaria con la API del mayorista)
    void updatePriceAndStock(String skuMayorista, BigDecimal nuevoPrecio, Integer nuevoStock);

    // 6. Eliminar un producto (posiblemente con la tabla Detalle asociada)
    void deleteById(Long id);
}