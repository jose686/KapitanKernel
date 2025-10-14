package com.laboratoriodecodigo.servicios;



import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ImagenesServicios {
    String guardarImagen(MultipartFile file) throws IOException;
    String obtenerUrlImagenPorId(Long id);
    List<String> listarTodasLasImagenes();
    void eliminarImagen(Long id);
}
