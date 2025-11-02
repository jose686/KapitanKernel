package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import com.laboratoriodecodigo.servicios.IMayoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/mayoristas")
public class MayoristaController {

    private final IMayoristaService mayoristaService;

    @Autowired
    public MayoristaController(IMayoristaService mayoristaService) {
        this.mayoristaService = mayoristaService;
    }

    /**
     * POST /api/v1/mayoristas
     * Registra un nuevo mayorista (proveedor).
     */
    @PostMapping
    public ResponseEntity<Mayorista> crearMayorista(@RequestBody Mayorista mayorista) {
        Mayorista nuevoMayorista = mayoristaService.guardar(mayorista);
        return new ResponseEntity<>(nuevoMayorista, HttpStatus.CREATED);
    }

    /**
     * GET /api/v1/mayoristas/{id}
     * Obtiene los detalles de un mayorista por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Mayorista> obtenerMayoristaPorId(@PathVariable Long id) {
        return mayoristaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // NOTA: Para buscar todos, necesitarías un método buscarTodos() en IMayoristaService/Repository.

    /**
     * DELETE /api/v1/mayoristas/{id}
     * Desactiva (elimina) un mayorista.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMayorista(@PathVariable Long id) {
        mayoristaService.desactivarMayorista(id);
        // Retorna 204 No Content, que indica éxito en la eliminación
        return ResponseEntity.noContent().build();
    }
}