package com.laboratoriodecodigo.paginaControlador;

import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/ia/busquedas") // Ruta para la interfaz de administración
public class BusquedaViewController {

    private final BusquedaPredefinidaService busquedaService;
    private final EtiquetaService etiquetaService;

    @Autowired
    public BusquedaViewController(BusquedaPredefinidaService busquedaService, EtiquetaService etiquetaService) {
        this.busquedaService = busquedaService;
        this.etiquetaService = etiquetaService;
    }

    /**
     * Muestra la tabla con todas las configuraciones de búsqueda y el formulario de creación.
     */
    @GetMapping
    public String mostrarPanelBusquedas(Model model) {
        // 1. Obtener listado de búsquedas existentes
        List<BusquedaPredefinida> busquedas = busquedaService.obtenerTodas();
        model.addAttribute("busquedas", busquedas);

        // 2. Obtener listado de etiquetas para el select del formulario
        List<Etiqueta> etiquetas = etiquetaService.obtenerTodas();
        model.addAttribute("etiquetas", etiquetas);

        // 3. Crear un objeto vacío para el formulario
        model.addAttribute("nuevaBusqueda", new BusquedaPredefinida());

        // La plantilla debe estar en src/main/resources/templates/ia/panel-busquedas.html
        return "ia/panel-busquedas";
    }

    /**
     * Procesa el formulario para crear una nueva configuración de búsqueda.
     */
    @PostMapping("/guardar")
    public String guardarBusqueda(BusquedaPredefinida nuevaBusqueda, @RequestParam Long idEtiqueta, RedirectAttributes redirectAttributes) {

        if (nuevaBusqueda.getPalabrasClave() == null || nuevaBusqueda.getPalabrasClave().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Las palabras clave son obligatorias.");
            return "redirect:/admin/ia/busquedas";
        }

        try {
            Etiqueta etiqueta = etiquetaService.buscarPorId(idEtiqueta);
            if (etiqueta == null) {
                redirectAttributes.addFlashAttribute("error", "Etiqueta no válida.");
                return "redirect:/admin/ia/busquedas";
            }

            // Asignación de la etiqueta y guardado
            nuevaBusqueda.setEtiqueta(etiqueta);
            busquedaService.guardarBusqueda(nuevaBusqueda);

            redirectAttributes.addFlashAttribute("mensaje", "Configuración de búsqueda guardada con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la configuración: " + e.getMessage());
        }

        // Redirige al GET para evitar el doble envío del formulario
        return "redirect:/admin/ia/busquedas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarBusqueda(@PathVariable("id") Long idBusqueda, RedirectAttributes redirectAttributes) {

        try {
            // Llama al servicio para ejecutar la lógica de borrado
            busquedaService.eliminarBusqueda(idBusqueda);

            redirectAttributes.addFlashAttribute("mensaje", "Configuración de búsqueda (ID: " + idBusqueda + ") eliminada con éxito.");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la configuración: " + e.getMessage());
        }

        // Redirige de vuelta al listado principal
        return "redirect:/admin/ia/busquedas";
    }

}