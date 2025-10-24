package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.Producto;
import com.laboratoriodecodigo.modelo.tienda.ProductoDetalle;

import java.util.Optional;

public interface IProductoDetalleService {
// 1. Guardar o actualizar un objeto ProductoDetalle (el contenido SEO)
ProductoDetalle save(ProductoDetalle detalle);

// 2. Buscar el detalle basado en el ID del producto principal
Optional<ProductoDetalle> findByProductoId(Long productoId);

// 3. Crear el detalle SEO inicial para un nuevo producto
ProductoDetalle createInitialDetail(Producto producto, String descripcionTecnicaMayorista);

// 4. Eliminar el detalle asociado a un producto
void deleteByProductoId(Long productoId);
}