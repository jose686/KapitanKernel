package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.tienda.Producto;
import com.laboratoriodecodigo.servicios.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final IProductoService productoService;

    @Autowired
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * GET /api/v1/productos
     * Lista todos los productos disponibles en la tienda (el catálogo).
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodosLosProductos() {
        List<Producto> productos = productoService.buscarTodos();
        return ResponseEntity.ok(productos);
    }

    /**
     * GET /api/v1/productos/{id}
     * Obtiene los detalles de un producto para la página de detalle.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/v1/productos
     * Crea un nuevo producto (útil para productos que no vienen del mayorista).
     */
    @PostMapping
    public ResponseEntity<Producto> crearOActualizarProducto(@RequestBody Producto producto) {
        // El método guardar maneja tanto la creación como la actualización
        Producto productoGuardado = productoService.guardar(producto);
        return ResponseEntity.ok(productoGuardado);
    }

    /**
     * DELETE /api/v1/productos/{id}
     * Elimina un producto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}