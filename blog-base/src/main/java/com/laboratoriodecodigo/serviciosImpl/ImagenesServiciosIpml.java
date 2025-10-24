package com.laboratoriodecodigo.serviciosImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;



import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.Imagenes;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.repositorio.ImagenesRepository;
import com.laboratoriodecodigo.repositorio.UsuarioRepository;
import com.laboratoriodecodigo.servicios.ImagenesServicios;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenesServiciosIpml implements ImagenesServicios {

    private final ImagenesRepository imagenRepository;
    private final Path rutaDeAlmacenamiento;
    private final UsuarioRepository usuarioRepository;


    public ImagenesServiciosIpml(ImagenesRepository imagenRepository, UsuarioRepository usuarioRepository) {
        this.imagenRepository = imagenRepository;
        this.usuarioRepository = usuarioRepository;
        String UPLOAD_DIR_STATIC = "blog-base/src/main/resources/static/uploads";

        this.rutaDeAlmacenamiento = Paths.get(UPLOAD_DIR_STATIC).toAbsolutePath().normalize();
        System.out.println("Ruta de Guardado de Imágenes: " + this.rutaDeAlmacenamiento.toString());

        try {
            Files.createDirectories(this.rutaDeAlmacenamiento);
        } catch (Exception ex) {

            throw new RuntimeException("No se pudo crear el directorio de almacenamiento estático: " + this.rutaDeAlmacenamiento, ex);
        }
    }

    @Override
    public Imagenes guardarImagen(MultipartFile file, Long idUsuario, String altText)
            throws IOException, RecursoNoEncontradoException {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + idUsuario));

        String nombreArchivo = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path rutaObjetivo = this.rutaDeAlmacenamiento.resolve(nombreArchivo);
            Files.copy(file.getInputStream(), rutaObjetivo, StandardCopyOption.REPLACE_EXISTING);
            Imagenes imagen = Imagenes.builder()
                    .nombreArchivo(nombreArchivo)
                    .ruta("/uploads/" + nombreArchivo)
                    .altText(altText)
                    .usuarioSubida(usuario)
                    .build();
            return imagenRepository.save(imagen);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("No se pudo guardar el archivo " + nombreArchivo + ". Causa: " + ex.getMessage());
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
    public List<Imagenes> listarTodasLasImagenes() {
        return imagenRepository.findAll();

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
