package com.laboratoriodecodigo.repositorio;



import com.laboratoriodecodigo.modelo.usuarios.TiposUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposUsuarioRepository extends JpaRepository<TiposUsuario, Long> {

    TiposUsuario findByNombreTipo(String nombreTipo);

}
