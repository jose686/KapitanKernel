package com.laboratoriodecodigo.servicios;






import com.laboratoriodecodigo.modelo.usuarios.TiposUsuario;

import java.util.List;




public interface TipoUsuarioServicios {


    TiposUsuario guardarTipoUsuario(TiposUsuario tiposUsuario);

    void eliminarTipoUsuario(Long id);

    TiposUsuario obtenerTipoUsuarioPorNombre(String nombre);

    List<TiposUsuario> listarTiposUsuario();

    

}
