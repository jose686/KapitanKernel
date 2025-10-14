package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.servicios.ImagenesServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "*")
public class ImagenController {

    private final ImagenesServicios imagenServicios;

    @Autowired
    public ImagenController(ImagenesServicios imagenServicios) {
        this.imagenServicios = imagenServicios;
    }

    @PostMapping("/subir")
    public ResponseEntity<String> subirImagen(@RequestParam("file") MultipartFile file) {
        try {
            String urlImagen = imagenServicios.guardarImagen(file);
            return new ResponseEntity<>(urlImagen, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al subir la imagen.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerUrlImagenPorId(@PathVariable Long id) {
        try {
            String urlImagen = imagenServicios.obtenerUrlImagenPorId(id);
            return ResponseEntity.ok(urlImagen);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> listarTodasLasImagenes() {
        List<String> urls = imagenServicios.listarTodasLasImagenes();
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Long id) {
        try {
            imagenServicios.eliminarImagen(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}