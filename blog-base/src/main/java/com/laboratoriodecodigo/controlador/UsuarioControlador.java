package com.laboratoriodecodigo.controlador;



import com.laboratoriodecodigo.modelo.Usuario;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControlador {

    private final UsuarioServicios usuarioServicios;

    @Autowired
    public UsuarioControlador(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios;
    }


    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        try {
            Usuario nuevoUsuario = usuarioServicios.guardarUsuario(usuario, usuario.getTipoUsuario().getNombreTipo());
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public  ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,@RequestBody Usuario usuarioActualizado) {

        try {
            Usuario usuarioGuardado = usuarioServicios.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(usuarioGuardado);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        try {
            usuarioServicios.eliminarUsuario(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (DataIntegrityViolationException e) {
            // En caso de que el usuario tenga datos asociados en otra tabla
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioServicios.listaUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }




}
