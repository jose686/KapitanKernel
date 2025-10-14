package com.laboratoriodecodigo.serviciosImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.Imagenes;
import com.laboratoriodecodigo.repositorio.ImagenesRepository;
import com.laboratoriodecodigo.servicios.ImagenesServicios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenesServiciosIpml implements ImagenesServicios {


    private final ImagenesRepository imagenRepository;
    private final Path rutaDeAlmacenamiento;

    public ImagenesServiciosIpml(ImagenesRepository imagenRepository, @Value("${file.upload-dir}") String uploadDir) {
        this.imagenRepository = imagenRepository;
        this.rutaDeAlmacenamiento = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.rutaDeAlmacenamiento);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear el directorio de almacenamiento.");
        }
    }

    @Override
    public String guardarImagen(MultipartFile file) throws IOException {
        String nombreArchivo = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Guarda el archivo en el sistema de ficheros
            Path rutaObjetivo = this.rutaDeAlmacenamiento.resolve(nombreArchivo);
            Files.copy(file.getInputStream(), rutaObjetivo, StandardCopyOption.REPLACE_EXISTING);

            // Guarda el registro en la base de datos
            Imagenes imagen = new Imagenes();
            imagen.setNombreArchivo(nombreArchivo);
            imagen.setRuta("/api/imagenes/" + nombreArchivo); // La URL para acceder al archivo
            Imagenes imagenGuardada = imagenRepository.save(imagen);

            return imagenGuardada.getRuta();

        } catch (IOException ex) {
            throw new IOException("No se pudo guardar el archivo " + nombreArchivo);
        }
    }

    @Override
    public String obtenerUrlImagenPorId(Long id) {
        Optional<Imagenes> imagenOptional = imagenRepository.findById(id);
        if (imagenOptional.isPresent()) {
            return imagenOptional.get().getRuta();
        } else {
            throw new RecursoNoEncontradoException("Imagen con ID " + id + " no encontrada.");
        }
    }

    @Override
    public List<String> listarTodasLasImagenes() {
        List<Imagenes> imagenes = imagenRepository.findAll();
        return imagenes.stream().map(Imagenes::getRuta).collect(Collectors.toList());
    }

    @Override
    public void eliminarImagen(Long id) {
        Optional<Imagenes> imagenOptional = imagenRepository.findById(id);
        if (!imagenOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Imagen con ID " + id + " no encontrada.");
        }
        Imagenes imagen = imagenOptional.get();


        try {
            Files.deleteIfExists(this.rutaDeAlmacenamiento.resolve(imagen.getNombreArchivo()));
        } catch (IOException e) {

        }


        imagenRepository.delete(imagen);
    }
}
