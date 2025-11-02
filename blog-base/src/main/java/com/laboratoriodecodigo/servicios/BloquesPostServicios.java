package com.laboratoriodecodigo.servicios;




import com.laboratoriodecodigo.modelo.blog.Bloques_Post;

import java.util.List;
import java.util.Optional;

public interface BloquesPostServicios {


    Bloques_Post guardarBloque(Long idPost, Bloques_Post bloquePost);

    Bloques_Post actualizarBloque(Long idPost, Long idBloque, Bloques_Post bloquePost);

    List<Bloques_Post> listarBloquesDePost(Long idPost);

    void eliminarBloque(Long idBloque);

    Optional<Bloques_Post> obtenerBloquePorId(Long idBloque);
}
