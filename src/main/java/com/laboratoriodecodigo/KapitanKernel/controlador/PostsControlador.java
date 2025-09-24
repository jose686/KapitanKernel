package com.laboratoriodecodigo.KapitanKernel.controlador;


import com.laboratoriodecodigo.KapitanKernel.excepciones.RecursoNoEncontradoException;
import com.laboratoriodecodigo.KapitanKernel.modelo.Posts;
import com.laboratoriodecodigo.KapitanKernel.servicios.PostsServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postsControlador")
@CrossOrigin(origins = "*")
public class PostsControlador {

    private final PostsServicios postsServicios;

    @Autowired
    public PostsControlador(PostsServicios postsServicios) {
        this.postsServicios = postsServicios;
    }

    @PostMapping
    public ResponseEntity<Posts> crearPost(@RequestBody Posts post, @RequestParam Long idAutor, @RequestParam List<Long> idsCategorias) {
        try {
            Posts nuevoPost = postsServicios.crearPost(post, idAutor, idsCategorias);
            return new ResponseEntity<>(nuevoPost, HttpStatus.CREATED);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Posts> actualizarPost(@PathVariable Long id, @RequestBody Posts postActualizado, @RequestParam Long idAutor, @RequestParam List<Long> idsCategorias) {
        try {
            Posts postActualizadoGuardado = postsServicios.actualizarPosts(id, postActualizado, idAutor, idsCategorias);
            return ResponseEntity.ok(postActualizadoGuardado);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Posts>> listarTodosLosPosts() {
        List<Posts> posts = postsServicios.listarPosts();
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPost(@PathVariable Long id) {
        try {
            postsServicios.eliminarPosts(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}