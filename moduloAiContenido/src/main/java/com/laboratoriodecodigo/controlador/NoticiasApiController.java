package com.laboratoriodecodigo.controlador;

import com.laboratoriodecodigo.servicios.INoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(name = "q", defaultValue = "tecnologia") String query,
            @RequestParam(name = "limit", defaultValue = "10") int limit)
    {
        // Esta lógica queda aquí, donde se gestiona la conexión a la API externa
        int totalGuardadas = noticiasService.sincronizarNoticiasDesdeAPI(query, limit);
        String mensaje = String.format("Sincronización completada. Total de noticias guardadas: %d...", totalGuardadas);
        return ResponseEntity.ok(mensaje);
    }
}