package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import com.laboratoriodecodigo.modelo.tienda.Producto;

import java.util.List;
import java.util.Optional;

public interface IMayoristaService {

    Optional<Mayorista> buscarPorId(Long id);

    // 2. Guardar o actualizar la configuración de un mayorista
    Mayorista guardar(Mayorista mayorista);

    // 3. Método clave: Conectar a la API y sincronizar el stock/precio
   // List<Producto> sincronizarCatalogo(Long mayoristaId);

    // 4. Desactivar un mayorista (si cortamos la relación comercial)
    void desactivarMayorista(Long id);
}
