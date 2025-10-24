package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import com.laboratoriodecodigo.modelo.tienda.Producto;

import java.util.List;
import java.util.Optional;

public interface IMayoristaService {// 1. Obtener la configuración de un mayorista
    Optional<Mayorista> findById(Long id);

    // 2. Guardar o actualizar la configuración de un mayorista
    Mayorista save(Mayorista mayorista);

    // 3. Método clave: Conectar a la API y sincronizar el stock/precio
    List<Producto> sincronizarCatalogo(Long mayoristaId);

    // 4. Desactivar un mayorista (si cortamos la relación comercial)
    void deactivateMayorista(Long id);
}
