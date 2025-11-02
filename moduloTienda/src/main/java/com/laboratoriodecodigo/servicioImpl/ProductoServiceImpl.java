package com.laboratoriodecodigo.servicioImpl;

import com.laboratoriodecodigo.modelo.tienda.Producto;
import com.laboratoriodecodigo.repositorio.ProductoRepositorio;
import com.laboratoriodecodigo.servicios.IProductoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProductoServiceImpl implements IProductoService {


    private final ProductoRepositorio productoRepositorio;

    @Autowired
    public ProductoServiceImpl(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public List<Producto> buscarTodos() {
        // ✅ DELEGA a JPA: Llama a findAll() del Repositorio
        return productoRepositorio.findAll();
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        // ✅ DELEGA a JPA: Llama a findById()
        return productoRepositorio.findById(id);
    }

    @Override
    public Optional<Producto> buscarPorSku(String skuMayorista) {
        return productoRepositorio.findBySkuMayoristaUnique(skuMayorista);
    }

    @Override
    public Producto guardar(Producto producto) {

        Producto guardado = productoRepositorio.save(producto);

        String accion = (producto.getId() == null) ? "guardado" : "actualizado";
        System.out.println("💾 Producto '" + guardado.getNombreProducto() + "' " + accion + ".");

        return guardado;
    }

    @Override
    public void actualizarPrecioYStock(String skuMayorista, BigDecimal nuevoPrecio, Integer nuevoStock) {
        Optional<Producto> productoOpt = buscarPorSku(skuMayorista);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();

            producto.setPrecioCostoDecimal(nuevoPrecio);
            producto.setStockInteger(nuevoStock);

            productoRepositorio.save(producto);

            System.out.println("✅ Actualizado SKU " + skuMayorista + ": Costo a " + nuevoPrecio + ", Stock a " + nuevoStock);
        } else {
            System.out.println("⚠️ SKU " + skuMayorista + " no encontrado para actualizar.");
        }
    }

    @Override
    public void eliminarPorId(Long id) {
        // ✅ DELEGA a JPA: Llama a deleteById()
        productoRepositorio.deleteById(id);
        System.out.println("🗑️ Producto ID " + id + " eliminado de la DB.");
    }
}