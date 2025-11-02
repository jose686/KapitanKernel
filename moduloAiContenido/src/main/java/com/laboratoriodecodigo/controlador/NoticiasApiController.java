package com.laboratoriodecodigo.controlador;

import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import com.laboratoriodecodigo.servicios.INoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/noticias") // URL más segura para endpoints administrativos
public class NoticiasApiController {

    private final INoticiasService noticiasService;

    @Autowired
    public NoticiasApiController(INoticiasService noticiasService) {
        this.noticiasService = noticiasService;
    }

    @GetMapping("/sincronizar")
    public ResponseEntity<String> sincronizarNoticias(
            // Cambiamos el defaultValue para que sea más descriptivo
            @RequestParam(name = "q", defaultValue = "inteligencia artificial") String query,
            @RequestParam(name = "limit", defaultValue = "10") int limit)
    {
        // El servicio recibe la cadena de consulta (ej. "ia,programacion") y el límite.
        int totalGuardadas = noticiasService.sincronizarNoticiasDesdeAPI(query, limit);
        String mensaje = String.format("Sincronización completada. Total de noticias guardadas: %d para la búsqueda: %s.", totalGuardadas, query);
        return ResponseEntity.ok(mensaje);
    }






}