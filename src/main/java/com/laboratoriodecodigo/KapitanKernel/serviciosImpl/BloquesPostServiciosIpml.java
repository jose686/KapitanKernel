package com.laboratoriodecodigo.KapitanKernel.serviciosImpl;

import com.laboratoriodecodigo.KapitanKernel.excepciones.RecursoNoEncontradoException;
import com.laboratoriodecodigo.KapitanKernel.modelo.Bloques_Post;
import com.laboratoriodecodigo.KapitanKernel.modelo.Posts;
import com.laboratoriodecodigo.KapitanKernel.repositorio.BloquesPostRepository;
import com.laboratoriodecodigo.KapitanKernel.servicios.BloquesPostServicios;
import com.laboratoriodecodigo.KapitanKernel.servicios.PostsServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloquesPostServiciosIpml implements BloquesPostServicios {


    private final BloquesPostRepository bloquesPostRepository;
    private final PostsServicios postsServicios;

    @Autowired
    public BloquesPostServiciosIpml(BloquesPostRepository bloquesPostRepository, PostsServicios postsServicios) {
        this.bloquesPostRepository = bloquesPostRepository;
        this.postsServicios = postsServicios;
    }

    @Override
    public Bloques_Post guardarBloque(Long idPost, Bloques_Post bloquePost) {
        Optional<Posts> postOptional = postsServicios.obtenerPostPorId(idPost);
        if (!postOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Post con ID " + idPost + " no encontrado.");
        }
        Posts post = postOptional.get();

        bloquePost.setPost(post);
        return bloquesPostRepository.save(bloquePost);
    }

    @Override
    public Bloques_Post actualizarBloque(Long idPost, Long idBloque, Bloques_Post bloquePost) {
        Optional<Posts> postOptional = postsServicios.obtenerPostPorId(idPost);
        if (!postOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Post con ID " + idPost + " no encontrado.");
        }
        Posts post = postOptional.get();

        Optional<Bloques_Post> bloqueOptional = bloquesPostRepository.findById(idBloque);
        if (!bloqueOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Bloque con ID " + idBloque + " no encontrado.");
        }
        Bloques_Post bloqueExistente = bloqueOptional.get();

        if (!bloqueExistente.getPost().equals(post)) {
            throw new IllegalArgumentException("El bloque no pertenece al post especificado.");
        }

        bloqueExistente.setContenido(bloquePost.getContenido());
        return bloquesPostRepository.save(bloqueExistente);
    }

    @Override
    public List<Bloques_Post> listarBloquesDePost(Long idPost) {
        Optional<Posts> postOptional = postsServicios.obtenerPostPorId(idPost);
        if (!postOptional.isPresent()) {
            throw new RecursoNoEncontradoException("Post con ID " + idPost + " no encontrado.");
        }
        Posts post = postOptional.get();

        return post.getBloquesDeContenido();
    }

}
