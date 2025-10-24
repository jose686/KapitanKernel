package com.laboratoriodecodigo.controlador;



import com.laboratoriodecodigo.modelo.blog.Bloques_Post;
import com.laboratoriodecodigo.servicios.BloquesPostServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class BloquesPostController {

    private final BloquesPostServicios bloquesPostServicios;

    @Autowired
    public BloquesPostController(BloquesPostServicios bloquesPostServicios) {
        this.bloquesPostServicios = bloquesPostServicios;
    }


    @PostMapping("/posts/{idPost}/bloques")
    public ResponseEntity<Bloques_Post> crearBloquePost(@PathVariable Long idPost, @RequestBody Bloques_Post bloquePost) {
        try {
            Bloques_Post nuevoBloque = bloquesPostServicios.guardarBloque(idPost, bloquePost);
            return new ResponseEntity<>(nuevoBloque, HttpStatus.CREATED);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/posts/{idPost}/bloques/{idBloque}")
    public ResponseEntity<Bloques_Post> actualizarBloquePost(@PathVariable Long idPost, @PathVariable Long idBloque, @RequestBody Bloques_Post bloquePost) {
        try {
            Bloques_Post bloqueActualizado = bloquesPostServicios.actualizarBloque(idPost, idBloque, bloquePost);
            return ResponseEntity.ok(bloqueActualizado);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
