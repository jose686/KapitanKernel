package com.laboratoriodecodigo.paginaControlador;


import com.laboratoriodecodigo.servicios.INoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/noticias") // URL más amigable para la interfaz
public class NoticiasViewController {

    private final INoticiasService noticiasService;

    @Autowired
    public NoticiasViewController(INoticiasService noticiasService) {
        this.noticiasService = noticiasService;
    }

    /**
     * Inicia la sincronización de noticias desde la API externa.
     * En lugar de devolver un JSON, redirige al panel de búsquedas con un mensaje.
     */
    @GetMapping("/sincronizar")
    public String sincronizarNoticias(
            // Utilizamos los mismos parámetros para definir qué y cuánto sincronizar
            @RequestParam(name = "q", defaultValue = "inteligencia artificial") String query,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            // Utilizamos RedirectAttributes para pasar mensajes después de la redirección
            RedirectAttributes redirectAttributes)
    {
        int totalGuardadas;

        try {
            // Llama al servicio para realizar la operación de negocio
            totalGuardadas = noticiasService.sincronizarNoticiasDesdeAPI(query, limit);

            String mensaje = String.format("Sincronización completada. Total de noticias guardadas: %d para la búsqueda: '%s'.", totalGuardadas, query);

            // Adjuntamos un mensaje de éxito para que se muestre en la página de destino
            redirectAttributes.addFlashAttribute("mensaje", mensaje);

        } catch (Exception e) {
            String error = String.format("Error durante la sincronización para la búsqueda '%s': %s", query, e.getMessage());
            // Adjuntamos un mensaje de error
            redirectAttributes.addFlashAttribute("error", error);
        }

        // Redirigimos al panel principal de gestión de búsquedas para ver el estado.
        // Asumo que quieres volver a la URL donde gestionas las búsquedas predefinidas
        return "redirect:/admin/ia/busquedas";
    }
}