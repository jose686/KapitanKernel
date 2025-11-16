package com.laboratoriodecodigo.paginaControlador;

import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/etiquetas") // Ruta para la interfaz de administración
public class EtiquetaViewController {

    private final EtiquetaService etiquetaService;

    @Autowired
    public EtiquetaViewController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    /**
     * Muestra la tabla de todas las etiquetas y el formulario para crear una nueva.
     */
    @GetMapping
    public String mostrarPanelEtiquetas(Model model) {
        // 1. Listado de etiquetas existentes
        List<Etiqueta> etiquetas = etiquetaService.obtenerTodas();
        model.addAttribute("etiquetas", etiquetas);

        // 2. Objeto vacío para el formulario de creación
        model.addAttribute("nuevaEtiqueta", new Etiqueta());

        // La plantilla debe estar en src/main/resources/templates/ia/panel-etiquetas.html
        return "ia/panel-etiquetas";
    }

    /**
     * Procesa el formulario para guardar o actualizar una etiqueta.
     */
    @PostMapping("/guardar")
    public String guardarEtiqueta(Etiqueta nuevaEtiqueta, RedirectAttributes redirectAttributes) {

        if (nuevaEtiqueta.getNombre() == null || nuevaEtiqueta.getNombre().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "El nombre de la etiqueta es obligatorio.");
            return "redirect:/admin/etiquetas";
        }

        try {
            // Lógica simple para evitar duplicados, si existe en el servicio
            if (etiquetaService.existePorNombre(nuevaEtiqueta.getNombre()) && nuevaEtiqueta.getIdEtiqueta() == null) {
                redirectAttributes.addFlashAttribute("error", "Ya existe una etiqueta con ese nombre.");
                return "redirect:/admin/etiquetas";
            }

            etiquetaService.guardarEtiqueta(nuevaEtiqueta);

            redirectAttributes.addFlashAttribute("mensaje", "Etiqueta guardada con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la etiqueta: " + e.getMessage());
        }

        // Redirige al GET para mostrar la lista actualizada
        return "redirect:/admin/etiquetas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEtiqueta(@PathVariable("id") Long idEtiqueta, RedirectAttributes redirectAttributes) {
        try {
            etiquetaService.eliminarEtiqueta(idEtiqueta);
            redirectAttributes.addFlashAttribute("mensaje", "Etiqueta eliminada con éxito.");
        } catch (Exception e) {
            // Este catch es importante si la etiqueta tiene noticias asociadas (restricción de clave externa)
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la etiqueta: " + e.getMessage());
        }

        // Redirige de vuelta al panel principal
        return "redirect:/admin/etiquetas";
    }

}