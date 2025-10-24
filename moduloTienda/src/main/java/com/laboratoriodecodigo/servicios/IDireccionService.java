package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.tienda.Direccion;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

public interface IDireccionService {
    Optional<Direccion> findById(Long id);

    // 2. Obtener todas las direcciones de un usuario
    List<Direccion> findByUsuario(Usuario usuario);

    // 3. Guardar una nueva dirección o actualizar una existente
    Direccion save(Direccion direccion);

    // 4. Marcar una dirección como la principal/por defecto
    void setAsDefault(Long direccionId, Long userId);
}
