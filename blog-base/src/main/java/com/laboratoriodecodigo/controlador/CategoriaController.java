package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.Categorias;
import com.laboratoriodecodigo.servicios.CategoriasServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriasServicios categoriaServicios;

    @Autowired
    public CategoriaController(CategoriasServicios categoriaServicios) {
        this.categoriaServicios = categoriaServicios;
    }


    @PostMapping
    public ResponseEntity<Categorias> crearCategoria(@RequestBody Categorias categoria) {
        Categorias nuevaCategoria = categoriaServicios.guardarCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> obtenerCategoriaPorId(@PathVariable Long id) {
        Optional<Categorias> categoriaOptional = categoriaServicios.obtenerCategoriaPorId(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Categorias>> listarTodasLasCategorias() {
        List<Categorias> categorias = categoriaServicios.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        try {
            categoriaServicios.eliminarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
