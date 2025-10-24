package com.laboratoriodecodigo.servicios;


import com.laboratoriodecodigo.modelo.usuarios.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioServicios {

    Usuario guardarUsuario(Usuario usuario, String nombreTipoUsuario);

    void eliminarUsuario(Long id);

    Usuario actualizarUsuario(Long id, Usuario usuarioActualizado);

    Optional <Usuario> obtenerUsuarioPorId(Long id);

    Optional<Usuario> obtenerUsuarioPorCorreo(String correo);

    Usuario obtenerUsuarioPorNombre(String nombre);

    List<Usuario> listaUsuarios();
}
