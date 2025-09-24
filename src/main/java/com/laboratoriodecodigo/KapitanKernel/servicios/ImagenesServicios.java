package com.laboratoriodecodigo.KapitanKernel.servicios;

import com.laboratoriodecodigo.KapitanKernel.modelo.Imagenes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImagenesServicios {
    String guardarImagen(MultipartFile file) throws IOException;
    String obtenerUrlImagenPorId(Long id);
    List<String> listarTodasLasImagenes();
    void eliminarImagen(Long id);
}
