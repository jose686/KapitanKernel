package com.laboratoriodecodigo.servicios;




import com.laboratoriodecodigo.modelo.blog.Posts;

import java.util.List;
import java.util.Optional;

public interface PostsServicios {


    //CRUD
    Posts crearPost (Posts posts, Long idAutor, List<Long> idsCategorias);

    Posts actualizarPosts (Long id, Posts postActualizado, Long idAutor, List<Long> idsCategorias);

    List<Posts> listarPosts ();

    void eliminarPosts (Long id);

    //Métodos de Búsqueda y Filtrado
    List<Posts> buscarPorTitulo(String titulo);

    List<Posts> buscarPorContenido(String palabraClave);

    List<Posts> filtrarPorCategoria(String nombreCategoria);

    List<Posts> obtenerUltimosPosts(int cantidad);

    // Métodos de Publicación y Estado

    Posts publicarPost(Long idPost);
    //Cambia el estado de un borrador a "publicado". Esto separa la acción de guardar de la acción de publicar

    Posts archivarPost(Long idPost);

    Optional<Posts> obtenerPostPorId(Long idPost);

}
