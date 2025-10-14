package com.laboratoriodecodigo.KapitanKernel.paginaControlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laboratoriodecodigo.KapitanKernel.modelo.Usuario;
import com.laboratoriodecodigo.KapitanKernel.servicios.TipoUsuarioServicios;
import com.laboratoriodecodigo.KapitanKernel.servicios.UsuarioServicios;

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
      
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("nuevoUsuario",new Usuario());
       
        return "crearUsuario";
    }

  



}
