package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/etiquetas") // Ruta dedicada solo a las etiquetas
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    @Autowired
    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @PostMapping
    public ResponseEntity<Etiqueta> guardarEtiqueta(@RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaGuardada = etiquetaService.guardarEtiqueta(etiqueta);
        return new ResponseEntity<>(etiquetaGuardada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Etiqueta>> obtenerTodasLasEtiquetas() {
        List<Etiqueta> etiquetas = etiquetaService.obtenerTodas();
        return new ResponseEntity<>(etiquetas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> buscarEtiquetaPorId(@PathVariable Long id) {
        Etiqueta etiqueta = etiquetaService.buscarPorId(id);
        if (etiqueta != null) {
            return new ResponseEntity<>(etiqueta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}



