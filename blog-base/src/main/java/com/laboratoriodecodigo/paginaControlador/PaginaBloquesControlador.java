package com.laboratoriodecodigo.paginaControlador;

import java.util.List;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.Bloques_Post;
import com.laboratoriodecodigo.modelo.blog.Posts;
import com.laboratoriodecodigo.servicios.BloquesPostServicios;
import com.laboratoriodecodigo.servicios.PostsServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PaginaBloquesControlador {




    private final BloquesPostServicios bloquesPostServicios;
    private final PostsServicios postsServicios;

    @Autowired
    public PaginaBloquesControlador(BloquesPostServicios bloquesPostServicios, PostsServicios postsServicios) {
        this.bloquesPostServicios = bloquesPostServicios;
        this.postsServicios = postsServicios;
    }






    @GetMapping("/posts/{idPost}/bloques")
    public String listarBloques (@PathVariable("idPost") Long idPost, Model model) {

        System.out.println("Entrando al controlador para el idPost: " + idPost);

        try {
            // 1. OBTENER POST CON FETCH JOIN:
            // Esta llamada (obtenerPostPorId) trae el Post, su colección de Bloques, y la Imagen de cada Bloque.
            Posts post = postsServicios.obtenerPostPorId(idPost)
                    .orElseThrow(() -> new RecursoNoEncontradoException("Post no encontrado."));

            // 2. ⭐ CORRECCIÓN CLAVE: Usar la colección de bloques ya cargada.
            // Asumo que la relación en la entidad Posts es getBloquesDeContenido()
            // y que devuelve un List o Set que se puede convertir a List.
            List<Bloques_Post> bloques = new java.util.ArrayList<>(post.getBloquesDeContenido());

            Bloques_Post nuevoBloque = new Bloques_Post();
            // Inicializamos la imagen para evitar NullPointerException al construir el formulario,
            // asumiendo que el constructor de Imagenes está en el paquete correcto.
            nuevoBloque.setImagen(com.laboratoriodecodigo.modelo.blog.Imagenes.builder().build());

            System.out.println("Bloques recuperados (del post cargado): " + bloques.size());

            // ⭐ DEBUG FINAL PARA VER LA RUTA ⭐
            if (!bloques.isEmpty() && bloques.get(0).getImagen() != null) {
                System.out.println("DEBUG RUTA IMAGEN BLOQUE 1: " + bloques.get(0).getImagen().getRuta());
            } else {
                System.out.println("DEBUG RUTA IMAGEN BLOQUE 1: NULL o Bloques vacíos.");
            }


            model.addAttribute("bloques", bloques);
            model.addAttribute("post", post);
            model.addAttribute("idPost", idPost);
            model.addAttribute("nuevoBloque", nuevoBloque);


            return "bloquesPost";

        } catch (RecursoNoEncontradoException e) {

            model.addAttribute("error", e.getMessage());
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{idPost}/bloques/guardar")
    public String guardarBloque(
            @PathVariable Long idPost,
            @ModelAttribute("nuevoBloque") Bloques_Post nuevoBloque,
            RedirectAttributes redirectAttributes
    ) {
        try {
            bloquesPostServicios.guardarBloque(idPost, nuevoBloque);

            redirectAttributes.addFlashAttribute("mensaje", "Bloque añadido con éxito al Post " + idPost + ".");
        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/posts/" + idPost + "/bloques";
    }
    @DeleteMapping("/posts/{idPost}/bloques/eliminar/{idBloque}")
    public String eliminarBloque(
            @PathVariable Long idPost,
            @PathVariable Long idBloque,
            RedirectAttributes redirectAttributes
    ) {
        try {
            bloquesPostServicios.eliminarBloque(idBloque);

            redirectAttributes.addFlashAttribute("mensaje", "Bloque " + idBloque + " eliminado con éxito.");
        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/posts/" + idPost + "/bloques";
    }
    @GetMapping("/posts/{idPost}/bloques/{idBloque}/editar")
    public String mostrarFormularioEdicionBloque(
            @PathVariable Long idPost,
            @PathVariable Long idBloque,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            postsServicios.obtenerPostPorId(idPost)
                    .orElseThrow(() -> new RecursoNoEncontradoException("Post con ID " + idPost + " no encontrado."));

            Bloques_Post bloqueAEditar = bloquesPostServicios.obtenerBloquePorId(idBloque) // Asumo que tienes este método en el servicio
                    .orElseThrow(() -> new RecursoNoEncontradoException("Bloque con ID " + idBloque + " no encontrado."));
            model.addAttribute("bloqueAEditar", bloqueAEditar);
            model.addAttribute("idPost", idPost);

            return "editarBloque"; // Nueva plantilla

        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/posts/" + idPost + "/bloques";
        }
    }

    @PutMapping("/posts/{idPost}/bloques/{idBloque}/editar")
    public String actualizarBloque(
            @PathVariable Long idPost,
            @PathVariable Long idBloque,
            @ModelAttribute("bloqueAEditar") Bloques_Post bloqueActualizado,
            RedirectAttributes redirectAttributes
    ) {
        try {
            // Llama a tu servicio, que debe manejar la actualización del contenido
            bloquesPostServicios.actualizarBloque(idPost, idBloque, bloqueActualizado);

            redirectAttributes.addFlashAttribute("mensaje", "Bloque " + idBloque + " actualizado.");
            return "redirect:/posts/" + idPost + "/bloques";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar bloque: " + e.getMessage());
            return "redirect:/posts/" + idPost + "/bloques";
        }
    }


}
