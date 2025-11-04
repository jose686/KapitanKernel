package com.laboratoriodecodigo.serviciosImpl;





import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.Categorias;
import com.laboratoriodecodigo.modelo.blog.Imagenes;
import com.laboratoriodecodigo.modelo.blog.PostStatus;
import com.laboratoriodecodigo.modelo.blog.Posts;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.repositorio.PostsRepository;
import com.laboratoriodecodigo.servicios.CategoriasServicios;
import com.laboratoriodecodigo.servicios.ImagenesServicios;
import com.laboratoriodecodigo.servicios.PostsServicios;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsServiciosIpml implements PostsServicios {

    private final PostsRepository postsRepository;
    private final UsuarioServicios usuarioServicios;
    private final CategoriasServicios categoriasServicios;
    private  final ImagenesServicios imagenesServicios;

    @Autowired
    public PostsServiciosIpml(PostsRepository postsRepository, UsuarioServicios usuarioServicios, CategoriasServicios categoriasServicios, ImagenesServicios imagenesServicios) {
        this.postsRepository = postsRepository;
        this.usuarioServicios = usuarioServicios;
        this.categoriasServicios = categoriasServicios;
        this.imagenesServicios = imagenesServicios;
    }


    // Archivo: PostsServiciosIpml.java

    @Override
    public Posts crearPost(Posts posts, Long idAutor, List<Long> idsCategorias, Long idImagenDestacada) {

        // 1. Validar y obtener el Autor
        Optional<Usuario> autorOptional = usuarioServicios.obtenerUsuarioPorId(idAutor);
        if (!autorOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Autor con ID " + idAutor + " no encontrado.");
        }
        Usuario autor = autorOptional.get();

        // 2. Validar y obtener las Categorías
        Set<Categorias> categorias = new HashSet<>();
        for (Long idCategoria : idsCategorias) {
            Optional<Categorias> categoriaOptional = categoriasServicios.obtenerCategoriaPorId(idCategoria);
            if (!categoriaOptional.isPresent()) {
                throw new RecursoNoEncontradoException("Categoría con ID " + idCategoria + " no encontrada.");
            }
            categorias.add(categoriaOptional.get());
        }

        // 3. ⭐ Obtener y asignar la Imagen Destacada ⭐
        if (idImagenDestacada != null) {
            Optional<Imagenes> imagenOptional = imagenesServicios.obtenerImagenPorId(idImagenDestacada);
            if (!imagenOptional.isPresent()) {
                throw new RecursoNoEncontradoException("Imagen con ID " + idImagenDestacada + " no encontrada.");
            }
            // Asignar el objeto Imagenes al Post. Funciona bien con ManyToOne.
            posts.setImagenDestacada(imagenOptional.get());
            // No es estrictamente necesario, pero podemos limpiar el campo de URL si existe el objeto Imagenes
            posts.setImagenDestacadaUrl(null);
        }

        // 4. Asignar el resto de las dependencias
        posts.setIdAutor(autor);
        posts.setCategorias(categorias);

        // 5. Guardar el Post (Hibernate se encarga de la OneToOne)
        return postsRepository.save(posts);
    }

    @Override
    public Posts actualizarPosts(
            Long idPost,
            Posts postActualizado,
            Long idAutor,
            List<Long> idsCategorias,
            Long idImagenDestacada // ⭐ Nuevo Parámetro ⭐
    ) {
        // 1. Obtener la entidad gestionada (Posts Existente)
        Posts postExistente = postsRepository.findById(idPost)
                .orElseThrow(() -> new RecursoNoEncontradoException("Post con ID " + idPost + " no encontrado."));

        // 2. Actualizar campos simples (Títulos, etc.)
        postExistente.setTitulo(postActualizado.getTitulo());
        postExistente.setMetaDescripcion(postActualizado.getMetaDescripcion());
        postExistente.setEstado(postActualizado.getEstado());
        // No tocar bloquesDeContenido aquí para evitar el error 500 (A collection with orphan deletion...)

        // 3. Actualizar Autor
        Usuario nuevoAutor = usuarioServicios.obtenerUsuarioPorId(idAutor)
                .orElseThrow(() -> new RecursoNoEncontradoException("Autor con ID " + idAutor + " no encontrado."));
        postExistente.setIdAutor(nuevoAutor);

        // 4. Actualizar Categorías
        Set<Categorias> nuevasCategorias = new HashSet<>();
        if (idsCategorias != null) {
            for (Long idCategoria : idsCategorias) {
                Categorias categoria = categoriasServicios.obtenerCategoriaPorId(idCategoria)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Categoría con ID " + idCategoria + " no encontrada."));
                nuevasCategorias.add(categoria);
            }
        }
        postExistente.setCategorias(nuevasCategorias);

        // 5. ⭐ Actualizar Imagen Destacada ⭐
        if (idImagenDestacada != null && idImagenDestacada > 0) { // Mayor a 0 para IDs válidos
            Imagenes imagen = imagenesServicios.obtenerImagenPorId(idImagenDestacada)
                    .orElseThrow(() -> new RecursoNoEncontradoException("Imagen con ID " + idImagenDestacada + " no encontrada."));

            postExistente.setImagenDestacada(imagen);
            postExistente.setImagenDestacadaUrl(null); // Aseguramos que solo se use el objeto
        } else {
            // Si el ID es nulo (o 0), se borra la imagen destacada
            postExistente.setImagenDestacada(null);

        }
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
    public Optional<Posts> obtenerPostPorId(Long id) {

        return postsRepository.findByIdWithFullContent(id);
    }

    public List<Posts> listarPostsPorEstado(PostStatus estado) {

        return postsRepository.findByEstado(estado);
    }
}
