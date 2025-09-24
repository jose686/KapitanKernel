package com.laboratoriodecodigo.KapitanKernel.paginaControlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laboratoriodecodigo.KapitanKernel.modelo.Categorias;
import com.laboratoriodecodigo.KapitanKernel.modelo.Posts;
import com.laboratoriodecodigo.KapitanKernel.servicios.CategoriasServicios;
import com.laboratoriodecodigo.KapitanKernel.servicios.PostsServicios;

@Controller
public class PaginaPost {

    private final PostsServicios postsServicios;
    private final CategoriasServicios categoriasServicios;
   
    @Autowired
    public PaginaPost(PostsServicios postsServicios, CategoriasServicios categoriasServicios) {
        this.postsServicios = postsServicios;
        this.categoriasServicios = categoriasServicios;
    }

/*
 * 
 @GetMapping("/posts")
 public String listarPost(Model model) {
    List<Posts> posts = postsServicios.listarPosts();
    
    model.addAttribute("posts",posts);
    model.addAttribute("nuevoPost",new Posts());
    return "post";
}
*/

    @GetMapping("/posts")
    public String listarPost(Model model) {
    List<Posts> posts = postsServicios.listarPosts(); 
    List<Categorias> todasLasCategorias = categoriasServicios.listarCategorias(); 
    model.addAttribute("posts", posts);
    model.addAttribute("todasLasCategorias", todasLasCategorias);
    model.addAttribute("nuevoPost", new Posts());
    return "post";
}
        


}
