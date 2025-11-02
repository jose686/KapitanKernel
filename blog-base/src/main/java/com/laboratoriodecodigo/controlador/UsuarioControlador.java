package com.laboratoriodecodigo.controlador;



import com.laboratoriodecodigo.modelo.blog.ActualizarPerfilDto;
import com.laboratoriodecodigo.modelo.blog.CambioPasswordDto;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication; // ⬅️ IMPORTANTE para obtener el usuario logueado
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/usuarios") // Ruta base
public class UsuarioControlador {

    private final UsuarioServicios usuarioServicios;

    @Autowired
    public UsuarioControlador(UsuarioServicios usuarioServicios) {
        this.usuarioServicios = usuarioServicios;
    }

    // =============================================================
    // ⭐ 1. ENDPOINT SEGURO: CAMBIAR CONTRASEÑA (Requiere JWT) ⭐
    // =============================================================
    @PostMapping("/cambiar-password")
    public ResponseEntity<String> cambiarPassword(
            @RequestBody CambioPasswordDto request,
            Authentication authentication // Inyecta la información del token JWT
    ) {
        // Obtiene el nombre del usuario logueado desde el token (ej. 'Administrador Principal')
        String nombreDeUsuario = authentication.getName();

        try {
            usuarioServicios.cambiarPassword(
                    nombreDeUsuario,
                    request.getCurrentPassword(),
                    request.getNewPassword()
            );
            return ResponseEntity.ok("Contraseña actualizada exitosamente. Por favor, vuelva a iniciar sesión.");

        } catch (RecursoNoEncontradoException e) {
            // Este caso es poco probable si el JWT es válido, pero es un buen control
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Usuario no encontrado.");
        } catch (RuntimeException e) {
            // Captura el error de contraseña actual incorrecta
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // =============================================================
    // ⭐ 2. ENDPOINT SEGURO: ACTUALIZAR PERFIL (Nombre y Correo) ⭐
    // =============================================================
    @PostMapping("/actualizar-perfil")
    public ResponseEntity<String> actualizarPerfil(
            @RequestBody ActualizarPerfilDto request,
            Authentication authentication
    ) {
        String nombreActual = authentication.getName();

        try {
            usuarioServicios.actualizarPerfil(nombreActual, request);
            return ResponseEntity.ok("Perfil actualizado exitosamente. Deberás iniciar sesión de nuevo si cambiaste el nombre.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public  ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,@RequestBody Usuario usuarioActualizado) {
        // ... (Tu lógica original de actualizar otro usuario por ID) ...
        try {
            Usuario usuarioGuardado = usuarioServicios.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(usuarioGuardado);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
