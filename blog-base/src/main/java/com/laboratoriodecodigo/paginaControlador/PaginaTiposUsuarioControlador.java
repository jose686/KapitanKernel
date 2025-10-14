package com.laboratoriodecodigo.paginaControlador;


import com.laboratoriodecodigo.modelo.TiposUsuario;
import com.laboratoriodecodigo.servicios.TipoUsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PaginaTiposUsuarioControlador {

    private final TipoUsuarioServicios tipoUsuarioServicios;

    @Autowired
    public PaginaTiposUsuarioControlador(TipoUsuarioServicios tipoUsuarioServicios) {
        this.tipoUsuarioServicios = tipoUsuarioServicios;
    }

    @GetMapping("/tipos-de-usuario")
    public String paginaTiposDeUsuario(Model model) {
        List<TiposUsuario> tipos = tipoUsuarioServicios.listarTiposUsuario();
        model.addAttribute("tipos", tipos);
        model.addAttribute("nuevoTipo", new TiposUsuario()); 
        return "tipos-de-usuario";
    }

    @PostMapping("/tipos-de-usuario/guardar")
    public String guardarTipoDeUsuario(@ModelAttribute("nuevoTipo") TiposUsuario tipoUsuario, RedirectAttributes redirectAttributes) {
        tipoUsuarioServicios.guardarTipoUsuario(tipoUsuario);
        redirectAttributes.addFlashAttribute("mensaje", "Rol guardado con éxito.");
        return "redirect:/tipos-de-usuario";
    }
/*
 * 
 @GetMapping("/tipos-de-usuario/editar/{id}")
 public String editarTipoDeUsuario(@PathVariable("id") Long id, Model model) {
    List<TiposUsuario> tipos = tipoUsuarioServicios.listarTiposUsuario();
    model.addAttribute("tipos", tipos);
    TiposUsuario tipoAEditar = tipoUsuarioServicios.obtenerTipoUsuarioPorId(id);
    model.addAttribute("nuevoTipo", tipoAEditar);
    return "tipos-de-usuario";
}
*/

    @GetMapping("/tipos-de-usuario/eliminar/{id}")
    public String eliminarTipoDeUsuario(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        tipoUsuarioServicios.eliminarTipoUsuario(id);
        redirectAttributes.addFlashAttribute("mensaje", "Rol eliminado con éxito.");
        return "redirect:/tipos-de-usuario";
    }
}
