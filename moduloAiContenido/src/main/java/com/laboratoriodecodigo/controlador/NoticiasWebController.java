package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.iaContenido.EstadoProcesamientoEnum;
import com.laboratoriodecodigo.modelo.iaContenido.NoticiaExterna;
import com.laboratoriodecodigo.servicios.INoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/noticias") // URL que consume el frontend (sin el /admin)
public class NoticiasWebController {


    private final INoticiasService noticiasService;

    @Autowired
    public NoticiasWebController(INoticiasService noticiasService) {
        this.noticiasService = noticiasService;
    }

    // Endpoint: /api/v1/noticias/ultimas
    @GetMapping("/ultimas")
    public ResponseEntity<List<NoticiaExterna>> getUltimasNoticias(
            @RequestParam(required = false, defaultValue = "25") int count,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String idioma,
            @RequestParam(required = false) String q) { // 'q' para búsqueda de texto

        // Llama al servicio con todos los filtros.
        // El servicio se encarga de aplicar solo los filtros que no son null.
        List<NoticiaExterna> noticias = noticiasService.findLatestFiltered(count, estado, idioma, q);

        if (noticias.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } else {
            return ResponseEntity.ok(noticias);
        }
    }

    @GetMapping("/porId")
    public ResponseEntity<NoticiaExterna> getNoticiaById(@RequestParam("id") String idNoticiaHash) {


        Optional<NoticiaExterna> noticia = noticiasService.findByIdNoticia(idNoticiaHash);

        if (noticia.isPresent()) {
            return ResponseEntity.ok(noticia.get());
        } else {
            // Devuelve 404 Not Found si no se encuentra
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/aprobar")
    public String aprobarNoticia(@PathVariable("id") String idNoticia, RedirectAttributes redirectAttributes) {
        try {
            noticiasService.cambiarEstado(idNoticia, EstadoProcesamientoEnum.APROBADO);
            redirectAttributes.addFlashAttribute("success", "Noticia aprobada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al aprobar la noticia: " + e.getMessage());
        }
        // Redirigir de vuelta a la página de listado
        return "redirect:/admin/noticias";
    }

    @PostMapping("/{id}/estado/{nuevoEstado}")
    public String cambiarEstadoNoticia(
            @PathVariable("id") String idNoticia,
            @PathVariable("nuevoEstado") EstadoProcesamientoEnum nuevoEstado, // Spring convierte la String a Enum automáticamente
            RedirectAttributes redirectAttributes) {

        try {
            // Llama al servicio con el estado dinámico
            noticiasService.cambiarEstado(idNoticia, nuevoEstado);
            redirectAttributes.addFlashAttribute("success", "Noticia cambiada a estado " + nuevoEstado.name() + ".");
            return "redirect:/admin/noticias";

        } catch (Exception e) {
            // Manejo de errores
            redirectAttributes.addFlashAttribute("error", "Error al cambiar el estado: " + e.getMessage());
            return "redirect:/admin/noticias";
        }
    }


    @GetMapping("/etiqueta/{nombreEtiqueta}")
    public ResponseEntity<List<NoticiaExterna>> obtenerNoticiasPorEtiqueta(
            @PathVariable String nombreEtiqueta,
            // ⭐ NUEVO: Recibir el estado como parámetro de consulta ⭐
            @RequestParam(required = false) String estado) {

        List<NoticiaExterna> noticias;

        if (estado != null && !estado.isEmpty()) {
            // Llama a un servicio que filtre por AMBOS
            noticias = noticiasService.buscarPorEtiquetaYEstado(nombreEtiqueta, estado);
        } else {
            // Comportamiento actual: solo etiqueta
            noticias = noticiasService.buscarPorEtiqueta(nombreEtiqueta);
        }

        return ResponseEntity.ok(noticias);
    }

}