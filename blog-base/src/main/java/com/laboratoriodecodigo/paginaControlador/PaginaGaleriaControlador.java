package com.laboratoriodecodigo.paginaControlador;

import org.springframework.ui.Model; // ESTA ES LA CORRECTA para pasar atributos a Thymeleaf
import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.Imagenes;
import com.laboratoriodecodigo.servicios.ImagenesServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.List;

@Controller
public class PaginaGaleriaControlador {

    private final ImagenesServicios imagenesServicios;

    @Autowired
    public PaginaGaleriaControlador(ImagenesServicios imagenesServicios) {
        this.imagenesServicios = imagenesServicios;
    }


    @GetMapping("/admin/galeria")
    public String listarGaleria(Model model) {
        List<Imagenes> imagenes = imagenesServicios.listarTodasLasImagenes();
        model.addAttribute("imagenes", imagenes);
        return "galeria"; // Devolvemos la nueva plantilla
    }

    @PostMapping("/admin/galeria/subir")
    public String subirImagen(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idUsuario") Long idUsuario, // Necesitas el ID del usuario que sube
            @RequestParam("altText") String altText,
            RedirectAttributes redirectAttributes
    ) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Por favor, selecciona un archivo para subir.");
            return "redirect:/admin/galeria";
        }

        try {
            // El servicio maneja el guardado en el sistema de archivos y en la base de datos
            imagenesServicios.guardarImagen(file, idUsuario, altText);
            redirectAttributes.addFlashAttribute("mensaje", "Imagen subida con éxito.");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al subir la imagen: " + e.getMessage());
        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/galeria";
    }


    @DeleteMapping("/admin/galeria/eliminar/{idImagen}")
    public String eliminarImagen(@PathVariable Long idImagen, RedirectAttributes redirectAttributes) {
        try {
            imagenesServicios.eliminarImagen(idImagen);
            redirectAttributes.addFlashAttribute("mensaje", "Imagen eliminada con éxito.");
        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/galeria";
    }

    @GetMapping("/admin/galeria/selector")
    public String selectorGaleria(Model model) {
        // La variable que se pasa al modelo
        List<Imagenes> imagenes = imagenesServicios.listarTodasLasImagenes();
        model.addAttribute("imagenes", imagenes); // <-- ¡Debe ser "imagenes"!

        return "galeriaSelector";
    }

}