package com.laboratoriodecodigo.controlador;



import com.laboratoriodecodigo.modelo.blog.Posts;
import com.laboratoriodecodigo.servicios.PostsServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postsControlador")
public class PostsControlador {

    private final PostsServicios postsServicios;

    @Autowired
    public PostsControlador(PostsServicios postsServicios) {
        this.postsServicios = postsServicios;
    }



}