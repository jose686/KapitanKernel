package com.laboratoriodecodigo.paginaControlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControlador {

    @GetMapping("/")
    public String index() {
        return "index"; // Busca templates/index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Esto busca src/main/resources/templates/login.html
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin"; // Busca templates/admin.html
    }

    @GetMapping("/admin/tipos-usuarios")
    public String tiposUsuarios() {
        // Sub-página para gestionar roles/tipos de usuario
        return "tipos_usuarios";
    }

    @GetMapping("/admin/crear-usuario")
    public String crearUsuario() {
        // Sub-página para el formulario de creación de usuarios
        return "crear_usuario";
    }


}
