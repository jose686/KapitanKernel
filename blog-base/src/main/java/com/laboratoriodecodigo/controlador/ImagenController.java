package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.blog.Imagenes;
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
public class ImagenController {

    private final ImagenesServicios imagenServicios;

    @Autowired
    public ImagenController(ImagenesServicios imagenServicios) {
        this.imagenServicios = imagenServicios;
    }

    @PostMapping
    public ResponseEntity<Imagenes> subirImagen(@RequestParam("file") MultipartFile file, @RequestParam("idUsuario") Long idUsuario, @RequestParam("altText") String altText
    ) {
        try {

            Imagenes nuevaImagen = imagenServicios.guardarImagen(file, idUsuario, altText);
            return new ResponseEntity<>(nuevaImagen, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RecursoNoEncontradoException e) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<List<Imagenes>> listarTodasLasImagenes() {
        List<Imagenes> imagenes = imagenServicios.listarTodasLasImagenes();
        return ResponseEntity.ok(imagenes);
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