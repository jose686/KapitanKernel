package com.laboratoriodecodigo.paginaControlador;

import java.util.List;
import java.util.Optional;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.Categorias;
import com.laboratoriodecodigo.modelo.blog.Imagenes;
import com.laboratoriodecodigo.modelo.blog.PostStatus;
import com.laboratoriodecodigo.modelo.blog.Posts;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.servicios.CategoriasServicios;
import com.laboratoriodecodigo.servicios.ImagenesServicios;
import com.laboratoriodecodigo.servicios.PostsServicios;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PaginaPost {

    private final PostsServicios postsServicios;
    private final CategoriasServicios categoriasServicios;
    private final UsuarioServicios usuarioServicios;
    private final ImagenesServicios imagenesServicios;

    @Autowired
    public PaginaPost(PostsServicios postsServicios, CategoriasServicios categoriasServicios, UsuarioServicios usuarioServicios, ImagenesServicios imagenesServicios) {
        this.postsServicios = postsServicios;
        this.categoriasServicios = categoriasServicios;
        this.usuarioServicios = usuarioServicios;
        this.imagenesServicios = imagenesServicios;
    }

    // En com.laboratoriodecodigo.paginaControlador.PaginaPost.java

    @GetMapping("/posts")
    public String listarPost(Model model) {
        List<Posts> posts = postsServicios.listarPosts();
        List<Categorias> todasLasCategorias = categoriasServicios.listarCategorias();

        // ⭐ NUEVO: Cargar todas las imágenes disponibles ⭐
        List<Imagenes> imagenesDisponibles = imagenesServicios.listarTodasLasImagenes();

        model.addAttribute("posts", posts);
        model.addAttribute("todasLasCategorias", todasLasCategorias);
        model.addAttribute("nuevoPost", new Posts());
        model.addAttribute("imagenesDisponibles", imagenesDisponibles); // ⭐ Añadir al Modelo ⭐
        return "post";
    }


    @PostMapping("/posts/guardar")
    public String guardarPost(
            @ModelAttribute("nuevoPost") Posts nuevoPost,
            @RequestParam("idAutor") Long idAutor,
            @RequestParam(value = "idsCategorias", required = false) List<Long> idsCategorias,
            @RequestParam(value = "idImagenDestacada", required = false) Long idImagenDestacada, // ⭐ NUEVO PARÁMETRO ⭐
            RedirectAttributes redirectAttributes
    ) {
        try {
            // Llamar al servicio con el nuevo ID de imagen
            postsServicios.crearPost(nuevoPost, idAutor, idsCategorias, idImagenDestacada); // ⭐ PASAR ID DE IMAGEN ⭐

            redirectAttributes.addFlashAttribute("mensaje", "Post creado con éxito.");
        } catch (RecursoNoEncontradoException e) {
            // Esto captura errores si el Autor, Categoría o la Imagen NO existen
            redirectAttributes.addFlashAttribute("error", "Error al crear el Post: " + e.getMessage());
        }

        return "redirect:/posts";
    }


    @DeleteMapping("/posts/eliminar/{idPost}")
    public String eliminarPost(@PathVariable Long idPost, RedirectAttributes redirectAttributes) {
        try {
            postsServicios.eliminarPosts(idPost);
            redirectAttributes.addFlashAttribute("mensaje", "Post con ID " + idPost + " eliminado con éxito.");
        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/editar/{idPost}")
    public String mostrarFormularioEdicion(@PathVariable Long idPost, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<Posts> postOptional = postsServicios.obtenerPostPorId(idPost);

            if (postOptional.isEmpty()) {
                throw new RecursoNoEncontradoException("Post con ID " + idPost + " no encontrado.");
            }
            Posts postAEditar = postOptional.get();
            List<Categorias> todasLasCategorias = categoriasServicios.listarCategorias();
            List<Usuario> autoresDisponibles = usuarioServicios.listaUsuarios();
            model.addAttribute("postAEditar", postAEditar);
            model.addAttribute("todasLasCategorias", todasLasCategorias);
            model.addAttribute("autoresDisponibles", autoresDisponibles);

            return "editarPost";

        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/posts";
        }
    }


    @PutMapping("/posts/editar/{idPost}")
    public String actualizarPost(
            @PathVariable Long idPost,
            @ModelAttribute("postAEditar") Posts postActualizado,
            @RequestParam("idAutor") Long idAutor,
            @RequestParam(value = "idsCategorias", required = false) List<Long> idsCategorias,
            @RequestParam(value = "idImagenDestacada", required = false) Long idImagenDestacada, // ⭐ NUEVO PARÁMETRO ⭐
            RedirectAttributes redirectAttributes
    ) {
        try {

            postsServicios.actualizarPosts(idPost, postActualizado, idAutor, idsCategorias, idImagenDestacada); // ⭐ PASAR ID DE IMAGEN ⭐

            redirectAttributes.addFlashAttribute("mensaje", "Post con ID " + idPost + " actualizado con éxito.");
            return "redirect:/posts";
        } catch (RecursoNoEncontradoException e) {
            // Esto también capturará el error si el objeto Imagenes no existe
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el Post: " + e.getMessage());
            return "redirect:/posts";
        }
    }

    @GetMapping("/blog/{id}")
    public String verPostDetalle(@PathVariable("id") Long idPost, Model model) {
        try {
            // 1. Obtener el post por ID (debe existir)
            Posts post = postsServicios.obtenerPostPorId(idPost)
                    .orElseThrow(() -> new RecursoNoEncontradoException("El post con ID " + idPost + " no existe."));

            // 2. Comprobar el estado (opcional: solo mostrar publicados)
            if (post.getEstado() != PostStatus.PUBLICADO) {
                // Podrías lanzar un error 404 o una página de acceso denegado si no está publicado
                throw new RecursoNoEncontradoException("El post no está disponible públicamente.");
            }
            model.addAttribute("post", post);
            return "post_detalle";

        } catch (RecursoNoEncontradoException e) {
            model.addAttribute("error", e.getMessage());
            return "error/404"; // Asumo que tienes una página de error 404
        }
    }
}
