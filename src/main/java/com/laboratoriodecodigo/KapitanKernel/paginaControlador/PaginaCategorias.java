package com.laboratoriodecodigo.KapitanKernel.paginaControlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laboratoriodecodigo.KapitanKernel.modelo.Categorias;
import com.laboratoriodecodigo.KapitanKernel.modelo.TiposUsuario;
import com.laboratoriodecodigo.KapitanKernel.servicios.CategoriasServicios;



@Controller
public class PaginaCategorias {

    private final CategoriasServicios categoriasServicios;

    @Autowired
    public PaginaCategorias(CategoriasServicios categoriasServicios) {
        this.categoriasServicios = categoriasServicios;
    }


    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categorias> categorias = categoriasServicios.listarCategorias();

        model.addAttribute("categorias", categorias);
        model.addAttribute("nuevaCategoria", new Categorias());

        return "categorias"; 
    }

 
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute("Categorias") Categorias categorias, RedirectAttributes redirectAttributes) {
        categoriasServicios.guardarCategoria(categorias);
        redirectAttributes.addFlashAttribute("mensaje", "Categorias guardado con éxito.");
        return "redirect:/categorias";
    }

    
    
   
    @GetMapping("/categorias/eliminar")
    public String eliminarCategoria(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        categoriasServicios.eliminarCategoria(id);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada con éxito.");
        return "redirect:/categorias";
    }
    

   
}
