package com.laboratoriodecodigo.KapitanKernel.paginaControlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PaginaImagenes {


    @GetMapping("/imagenes")
    public String mostarPagina() {
        return "imagenes"; // Devuelve el nombre del archivo HTML (mi-pagina.html)
    }
    

}
