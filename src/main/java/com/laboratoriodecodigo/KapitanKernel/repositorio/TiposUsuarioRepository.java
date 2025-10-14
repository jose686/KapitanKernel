package com.laboratoriodecodigo.KapitanKernel.repositorio;

import com.laboratoriodecodigo.KapitanKernel.modelo.TiposUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposUsuarioRepository extends JpaRepository<TiposUsuario, Long> {

    TiposUsuario findByNombreTipo(String nombreTipo);

}
