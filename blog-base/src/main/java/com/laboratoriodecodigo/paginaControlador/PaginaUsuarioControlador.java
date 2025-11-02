package com.laboratoriodecodigo.paginaControlador;

import java.util.List;
import java.util.Optional;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.usuarios.TiposUsuario;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.servicios.TipoUsuarioServicios;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PaginaUsuarioControlador {
    
    private final UsuarioServicios usuarioServicios;
    private final TipoUsuarioServicios tipoUsuarioServicios;

    @Autowired
    public PaginaUsuarioControlador(UsuarioServicios usuarioServicios, TipoUsuarioServicios usuarioControlador) {
        this.usuarioServicios = usuarioServicios;
        this.tipoUsuarioServicios = usuarioControlador;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {

        List<Usuario> usuarios = usuarioServicios.listaUsuarios();
        List<TiposUsuario> tiposDisponibles = tipoUsuarioServicios.listarTiposUsuario();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("nuevoUsuario", new Usuario());
        model.addAttribute("tiposDisponibles", tiposDisponibles);

        return "crearUsuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") Usuario usuario, RedirectAttributes redirectAttributes) {

        Long idTipoUsuario = usuario.getTipoUsuario().getIdTipo();
        usuarioServicios.guardarUsuario(usuario, idTipoUsuario);

        redirectAttributes.addFlashAttribute("mensaje", "Usuario creado con éxito.");
        return "redirect:/usuarios";
    }



    @DeleteMapping("/usuarios/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable Long idUsuario, RedirectAttributes redirectAttributes) {

        try {
            usuarioServicios.eliminarUsuario(idUsuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado con éxito.");
        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (DataIntegrityViolationException e) {
            // En caso de que el usuario tenga pedidos o datos relacionados
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el usuario porque tiene datos asociados.");
        }

        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{idUsuario}")
    public String mostrarFormularioEdicion(@PathVariable Long idUsuario, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<Usuario> usuarioOptional = usuarioServicios.obtenerUsuarioPorId(idUsuario);

            if (usuarioOptional.isEmpty()) {
                throw new RecursoNoEncontradoException("Usuario con ID " + idUsuario + " no encontrado para editar.");
            }

            Usuario usuarioAEditar = usuarioOptional.get();
            List<TiposUsuario> tiposDisponibles = tipoUsuarioServicios.listarTiposUsuario();
            model.addAttribute("usuarioAEditar", usuarioAEditar);
            model.addAttribute("tiposDisponibles", tiposDisponibles);
            return "editarUsuario";

        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuarios";
        }
    }



    @PutMapping("/usuarios/editar/{idUsuario}")
    public String actualizarUsuario(
            @PathVariable Long idUsuario,
            @ModelAttribute("usuarioAEditar") Usuario usuarioActualizado,
            // ⭐ NUEVO: Capturar la contraseña si se envía en el formulario
            @RequestParam(value = "nuevaContrasena", required = false) String nuevaContrasena,
            RedirectAttributes redirectAttributes
    ) {
        try {
            // 1. Actualizar datos básicos (nombre, correo, rol)
            usuarioServicios.actualizarUsuario(idUsuario, usuarioActualizado);

            // 2. ⭐ LÓGICA DE RESETEADO DE CONTRASEÑA ⭐
            if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                // Se llama a un nuevo método de servicio que solo fuerza el cambio
                usuarioServicios.resetPasswordByAdmin(idUsuario, nuevaContrasena);
                redirectAttributes.addFlashAttribute("alerta", "Contraseña del usuario ID " + idUsuario + " ha sido reseteada.");
            }

            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado con éxito.");
            return "redirect:/usuarios";

        } catch (RecursoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuarios";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar usuario: " + e.getMessage());
            return "redirect:/usuarios";
        }
    }
}

