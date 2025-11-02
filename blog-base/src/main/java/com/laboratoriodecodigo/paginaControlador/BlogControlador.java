package com.laboratoriodecodigo.paginaControlador;

import org.springframework.ui.Model;
import com.laboratoriodecodigo.modelo.blog.Posts;
import com.laboratoriodecodigo.servicios.PostsServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogControlador {


    private PostsServicios postServicio; // Asumiendo que tienes este servicio
    @Autowired
    public BlogControlador(PostsServicios postServicio) {
        this.postServicio = postServicio;
    }


    @GetMapping("/blog")
    public String listarPosts(Model model) {

        List<Posts> posts = postServicio.listarPosts();
        model.addAttribute("posts", posts);

        return "listadoPosts";
    }
}