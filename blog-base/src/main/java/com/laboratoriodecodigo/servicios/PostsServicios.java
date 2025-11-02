package com.laboratoriodecodigo.servicios;




import com.laboratoriodecodigo.modelo.blog.Posts;

import java.util.List;
import java.util.Optional;

public interface PostsServicios {





    Posts crearPost(Posts posts, Long idAutor, List<Long> idsCategorias, Long idImagenDestacada);

    Posts actualizarPosts(Long idPost, Posts postActualizado, Long idAutor, List<Long> idsCategorias, Long idImagenDestacada);

    List<Posts> listarPosts ();

    void eliminarPosts (Long id);


    Optional<Posts> obtenerPostPorId(Long idPost);

}
