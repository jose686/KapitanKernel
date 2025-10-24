package com.laboratoriodecodigo.servicios;



import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.Imagenes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ImagenesServicios {
    Imagenes guardarImagen(MultipartFile file, Long idUsuario, String altText) throws IOException, RecursoNoEncontradoException;
    String obtenerUrlImagenPorId(Long id);
    List<Imagenes> listarTodasLasImagenes();
    void eliminarImagen(Long id);
}
