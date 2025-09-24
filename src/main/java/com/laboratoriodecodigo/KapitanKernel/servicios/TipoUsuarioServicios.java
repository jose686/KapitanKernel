package com.laboratoriodecodigo.KapitanKernel.servicios;


import com.laboratoriodecodigo.KapitanKernel.modelo.TiposUsuario;
import com.laboratoriodecodigo.KapitanKernel.modelo.Usuario;

import java.util.List;
import java.util.Optional;



public interface TipoUsuarioServicios {


    TiposUsuario guardarTipoUsuario(TiposUsuario tiposUsuario);

    void eliminarTipoUsuario(Long id);

    TiposUsuario obtenerTipoUsuarioPorNombre(String nombre);

    List<TiposUsuario> listarTiposUsuario();

    

}
