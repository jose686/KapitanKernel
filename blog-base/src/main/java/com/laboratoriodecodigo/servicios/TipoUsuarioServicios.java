package com.laboratoriodecodigo.servicios;






import com.laboratoriodecodigo.modelo.usuarios.TiposUsuario;

import java.util.List;
import java.util.Optional;


public interface TipoUsuarioServicios {


    TiposUsuario guardarTipoUsuario(TiposUsuario tiposUsuario);

    void eliminarTipoUsuario(Long id);

    TiposUsuario obtenerTipoUsuarioPorNombre(String nombre);

    TiposUsuario obtenerTipoUsuarioPorId(long id);


    List<TiposUsuario> listarTiposUsuario();

    

}
