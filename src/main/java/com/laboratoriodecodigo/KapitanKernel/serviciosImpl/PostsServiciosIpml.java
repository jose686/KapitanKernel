package com.laboratoriodecodigo.KapitanKernel.serviciosImpl;

import com.laboratoriodecodigo.KapitanKernel.excepciones.RecursoNoEncontradoException;
import com.laboratoriodecodigo.KapitanKernel.modelo.Categorias;
import com.laboratoriodecodigo.KapitanKernel.modelo.Posts;
import com.laboratoriodecodigo.KapitanKernel.modelo.Usuario;
import com.laboratoriodecodigo.KapitanKernel.repositorio.PostsRepository;
import com.laboratoriodecodigo.KapitanKernel.servicios.CategoriasServicios;
import com.laboratoriodecodigo.KapitanKernel.servicios.PostsServicios;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.laboratoriodecodigo.KapitanKernel.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsServiciosIpml implements PostsServicios {

    private final PostsRepository postsRepository;
    private final UsuarioServicios usuarioServicios;
    private final CategoriasServicios  categoriasServicios;

    @Autowired
    public PostsServiciosIpml(PostsRepository postsRepository, UsuarioServicios usuarioServicios, CategoriasServicios categoriasServicios) {
        this.postsRepository = postsRepository;
        this.usuarioServicios = usuarioServicios;
        this.categoriasServicios = categoriasServicios;
    }



    @Override
    public Posts crearPost(Posts posts,Long idAutor, List<Long> idsCategorias) {


        Optional<Usuario> autorOptional = usuarioServicios.obtenerUsuarioPorId(idAutor);
        if (!autorOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Autor con ID " + idAutor + " no encontrado.");
        }
        Usuario autor = autorOptional.get();


        Set<Categorias> categorias = new HashSet<>();
        for (Long idCategoria : idsCategorias) {
            Optional<Categorias> categoriaOptional = categoriasServicios.obtenerCategoriaPorId(idCategoria);
            if (!categoriaOptional.isPresent()) {
                throw new RecursoNoEncontradoException("Categoría con ID " + idCategoria + " no encontrada.");
            }
            categorias.add(categoriaOptional.get());
        }

        posts.setIdAutor(autor);
        posts.setCategorias(categorias);
        return postsRepository.save(posts);
    }

    public Posts actualizarPosts (Long id, Posts postActualizado, Long idAutor, List<Long> idsCategorias) {

        // 1. Obtener el post original de la base de datos
        Optional<Posts> postOptional = postsRepository.findById(id);
        if (!postOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Post con ID " + id + " no encontrado.");
        }
        Posts postExistente = postOptional.get();

        // 2. Validar y obtener el Autor
        Optional<Usuario> autorOptional = usuarioServicios.obtenerUsuarioPorId(idAutor);
        if (!autorOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Autor con ID " + idAutor + " no encontrado.");
        }
        Usuario autor = autorOptional.get();

        // 3. Validar y obtener las Categorías
        Set<Categorias> categorias = new HashSet<>();
        for (Long idCategoria : idsCategorias) {
            Optional<Categorias> categoriaOptional = categoriasServicios.obtenerCategoriaPorId(idCategoria);
            if (!categoriaOptional.isPresent()) {
                throw new RecursoNoEncontradoException("Categoría con ID " + idCategoria + " no encontrada.");
            }
            categorias.add(categoriaOptional.get());
        }

        // 4. Actualizar los campos del post existente
        postExistente.setTitulo(postActualizado.getTitulo());
        postExistente.setMetaDescripcion(postActualizado.getMetaDescripcion());
        postExistente.setFechaPublicacion(postActualizado.getFechaPublicacion());
        postExistente.setEstado(postActualizado.getEstado());
        postExistente.setBloquesDeContenido(postActualizado.getBloquesDeContenido());
        postExistente.setIdAutor(autor);
        postExistente.setCategorias(categorias);

        // 5. Guardar el post actualizado y lo devuelve
        return postsRepository.save(postExistente);
    }

    @Override
    public List<Posts> listarPosts() {
        return postsRepository.findAll();
    }

    @Override
    public void eliminarPosts(Long id) {
        if (!postsRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Post con ID " + id + " no encontrado.");
        }
        postsRepository.deleteById(id);
    }

    @Override
    public List<Posts> buscarPorTitulo(String titulo) {
        return List.of();
    }

    @Override
    public List<Posts> buscarPorContenido(String palabraClave) {
        return List.of();
    }

    @Override
    public List<Posts> filtrarPorCategoria(String nombreCategoria) {
        return List.of();
    }

    @Override
    public List<Posts> obtenerUltimosPosts(int cantidad) {
        return List.of();
    }

    @Override
    public Posts publicarPost(Long idPost) {
        return null;
    }

    @Override
    public Posts archivarPost(Long idPost) {
        return null;
    }

    @Override
    public Optional<Posts> obtenerPostPorId(Long id) {

        return postsRepository.findById(id);
    }
}
