package com.laboratoriodecodigo.servicios;


import com.laboratoriodecodigo.modelo.tienda.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// CONTRATO para manejar las operaciones del Producto Principal (Mayorista)
public interface IProductoService {

    // 1. Cargar el catálogo completo o paginado
    List<Producto> buscarTodos();

    // 2. Buscar un producto por su ID de nuestra base de datos
    Optional<Producto> buscarPorId(Long id);

    // 3. Buscar un producto por su SKU de mayorista (clave única)
    Optional<Producto> buscarPorSku(String skuMayorista);

    // 4. Guardar un producto nuevo o actualizar uno existente
    Producto guardar(Producto producto);

    // 5. Actualizar solo el precio y el stock (usado en la sincronización diaria con la API del mayorista)
    void actualizarPrecioYStock(String skuMayorista, BigDecimal nuevoPrecio, Integer nuevoStock);

    // 6. Eliminar un producto (posiblemente con la tabla Detalle asociada)
    void eliminarPorId(Long id);


}