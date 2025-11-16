package com.laboratoriodecodigo.paginaControlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController {


    @GetMapping("/error/403")
    public String accessDenied(Model model) {
        model.addAttribute("titulo", "Acceso Denegado");
        model.addAttribute("mensaje", "Lo sentimos, no tiene permiso para acceder a este recurso.");
        return "error/403";
    }


}
