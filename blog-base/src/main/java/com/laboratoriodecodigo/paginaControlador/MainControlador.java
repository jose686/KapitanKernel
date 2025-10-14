package com.laboratoriodecodigo.paginaControlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControlador {

    @GetMapping("/")
    public String index(){

        return "index";
    }
}
