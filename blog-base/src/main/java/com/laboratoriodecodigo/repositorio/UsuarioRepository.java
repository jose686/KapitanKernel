package com.laboratoriodecodigo.repositorio;



import com.laboratoriodecodigo.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Usuario findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByCorreo(String correo);
    boolean existsByTipoUsuarioIdTipo(Long idTipo);

}
