package com.laboratoriodecodigo.paginaControlador;


import com.laboratoriodecodigo.modelo.iaContenido.NoticiaExterna; // Asumo que este es tu modelo
import com.laboratoriodecodigo.modelo.iaContenido.EstadoProcesamientoEnum; // Asumo este Enum
import com.laboratoriodecodigo.servicios.INoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/noticias")
public class NoticiasAdminViewController {

    private final INoticiasService noticiasService;

    @Autowired
    public NoticiasAdminViewController(INoticiasService noticiasService) {
        this.noticiasService = noticiasService;
    }

    /**
     * Muestra el panel de administración de noticias con filtros.
     * Reemplaza la funcionalidad de GET /api/v1/noticias/ultimas para el administrador.
     */
    @GetMapping
    public String mostrarPanelNoticias(
            Model model,
            @RequestParam(required = false, defaultValue = "25") int count,
            @RequestParam(required = false, defaultValue = "PENDIENTE") String estado,
            @RequestParam(required = false) String q)
    {
        // 1. Obtener listado de noticias (aquí solo usamos estado y count para simplicidad)
        // El estado 'PENDIENTE' es útil para que el administrador vea lo que debe revisar.
        List<NoticiaExterna> noticias = noticiasService.findLatestFiltered(count, estado, null, q); // null para idioma

        model.addAttribute("noticias", noticias);
        model.addAttribute("estadoActual", estado); // Pasa el estado para la UI
        model.addAttribute("estadosDisponibles", EstadoProcesamientoEnum.values()); // Pasa el Enum para el selector

        // La plantilla debe estar en src/main/resources/templates/ia/panel-noticias-admin.html
        return "ia/panel-noticias-admin";
    }

    /**
     * Migrado del NoticiasWebController: Cambia el estado de una noticia a APROBADO.
     */
    @PostMapping("/{id}/aprobar")
    public String aprobarNoticia(@PathVariable("id") String idNoticia, RedirectAttributes redirectAttributes) {
        try {
            // Llama al servicio, ya no se requiere el Enum.
            noticiasService.cambiarEstado(idNoticia, EstadoProcesamientoEnum.APROBADO);
            redirectAttributes.addFlashAttribute("mensaje", "Noticia aprobada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al aprobar la noticia: " + e.getMessage());
        }
        // Redirigir de vuelta a la página actual de administración
        return "redirect:/admin/noticias";
    }

    /**
     * Migrado del NoticiasWebController: Cambia el estado de una noticia a cualquier otro estado.
     */
    @PostMapping("/{id}/estado/{nuevoEstado}")
    public String cambiarEstadoNoticia(
            @PathVariable("id") String idNoticia,
            @PathVariable("nuevoEstado") EstadoProcesamientoEnum nuevoEstado,
            RedirectAttributes redirectAttributes) {

        try {
            noticiasService.cambiarEstado(idNoticia, nuevoEstado);
            redirectAttributes.addFlashAttribute("mensaje", "Noticia cambiada a estado " + nuevoEstado.name() + ".");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cambiar el estado: " + e.getMessage());
        }

        // Redirigir de vuelta a la página actual de administración
        return "redirect:/admin/noticias";
    }
}