package com.laboratoriodecodigo.repositorio;



import com.laboratoriodecodigo.modelo.TiposUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposUsuarioRepository extends JpaRepository<TiposUsuario, Long> {

    TiposUsuario findByNombreTipo(String nombreTipo);

}
