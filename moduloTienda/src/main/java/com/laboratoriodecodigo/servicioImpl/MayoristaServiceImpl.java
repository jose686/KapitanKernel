package com.laboratoriodecodigo.servicioImpl;


import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import com.laboratoriodecodigo.modelo.tienda.Producto;

import com.laboratoriodecodigo.repositorio.MayoristaRepositorio;
import com.laboratoriodecodigo.servicios.IMayoristaService;
import com.laboratoriodecodigo.servicios.IProductoService; // Necesitas esta dependencia
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MayoristaServiceImpl implements IMayoristaService {

    private final MayoristaRepositorio mayoristaRepositorio;
    private final IProductoService productoService; // Inyectamos el servicio de Producto para la sincronización

    @Autowired
    public MayoristaServiceImpl(MayoristaRepositorio mayoristaRepositorio, IProductoService productoService) {
        this.mayoristaRepositorio = mayoristaRepositorio;
        this.productoService = productoService;
    }

    @Override
    public Optional<Mayorista> buscarPorId(Long id) {
        // ✅ DELEGA a JPA: Llama al método findById() del repositorio
        return mayoristaRepositorio.findById(id);
    }

    @Override
    public Mayorista guardar(Mayorista mayorista) {
        // ✅ DELEGA a JPA: save() se encarga de INSERTAR (si ID es nulo) o ACTUALIZAR.
        Mayorista guardado = mayoristaRepositorio.save(mayorista);
        System.out.println("✅ Mayorista '" + guardado.getNombre() + "' registrado con éxito. ID: " + guardado.getIdMayorista());
        return guardado;
    }


    @Override
    public void desactivarMayorista(Long id) {
        // ✅ DELEGA a JPA: Borra el registro de MySQL
        mayoristaRepositorio.deleteById(id);
        System.out.println("⚠️ Mayorista ID " + id + " desactivado (eliminado de la DB).");
    }


}
