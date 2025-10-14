package com.laboratoriodecodigo.paginaControlador;

import java.util.List;


import com.laboratoriodecodigo.modelo.Bloques_Post;
import com.laboratoriodecodigo.servicios.BloquesPostServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.ui.Model;


@Controller
public class PaginaBloquesControlador {

    private final BloquesPostServicios bloquesPostServicios;

    @Autowired
    public PaginaBloquesControlador(BloquesPostServicios bloquesPostServicios) {
        this.bloquesPostServicios = bloquesPostServicios;
    }


    @GetMapping("/bloques/listar")
    public String listarBloques (@RequestParam("idTipo") Long idPost, Model model) {
        System.out.println("Entrando al controlador para el idPost: " + idPost);
        List<Bloques_Post> bloques = bloquesPostServicios.listarBloquesDePost(idPost);
        System.out.println("Bloques recuperados: " + bloques.size());
        model.addAttribute("bloques", bloques);
        model.addAttribute("idPost", idPost);
        return "crearBloques";
    }


}
