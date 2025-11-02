package com.laboratoriodecodigo.paginaControlador;

import java.util.List;
import java.util.Optional;


import com.laboratoriodecodigo.modelo.blog.Categorias;
import com.laboratoriodecodigo.servicios.CategoriasServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import org.springframework.web.bind.annotation.*;

@Controller
public class PaginaCategorias {

    private final CategoriasServicios categoriasServicios;

    @Autowired
    public PaginaCategorias(CategoriasServicios categoriasServicios) {
        this.categoriasServicios = categoriasServicios;
    }

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categorias> categorias = categoriasServicios.listarCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("nuevaCategoria", new Categorias());
        return "categorias";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute("nuevaCategoria") Categorias categorias, RedirectAttributes redirectAttributes) {
        // Corregido: Usar 'nuevaCategoria' para el binding si es el que usas en el modelo
        categoriasServicios.guardarCategoria(categorias);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría guardada con éxito.");
        return "redirect:/categorias";
    }

    // =============================================================
    // ⭐ NUEVO: MOSTRAR FORMULARIO DE EDICIÓN ⭐
    // =============================================================
    @GetMapping("/categorias/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<Categorias> categoriaOptional = categoriasServicios.obtenerCategoriaPorId(id);

            if (categoriaOptional.isEmpty()) {
                throw new RuntimeException("Categoría con ID " + id + " no encontrada para editar.");
            }

            Categorias categoriaAEditar = categoriaOptional.get();
            model.addAttribute("categoriaAEditar", categoriaAEditar);
            return "editarCategoria";

        } catch (RuntimeException e) { // Captura cualquier error, incluyendo la categoría no encontrada
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/categorias";
        }
    }

    // =============================================================
    // ⭐ NUEVO: PROCESAR EDICIÓN (ACTUALIZAR) ⭐
    // =============================================================
    @PostMapping("/categorias/actualizar/{id}") // Usamos POST ya que los formularios web no manejan PUT/PATCH bien
    public String actualizarCategoria(@PathVariable Long id,
                                      @ModelAttribute("categoriaAEditar") Categorias categoriaActualizada,
                                      RedirectAttributes redirectAttributes) {
        try {
            categoriasServicios.actualizarCategoria(id, categoriaActualizada);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría actualizada con éxito.");
            return "redirect:/categorias";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la categoría: " + e.getMessage());
            return "redirect:/categorias";
        }
    }

    // Modificando /eliminar para usar la ruta REST más limpia
    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriasServicios.eliminarCategoria(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada con éxito.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/categorias";
    }
}
   

