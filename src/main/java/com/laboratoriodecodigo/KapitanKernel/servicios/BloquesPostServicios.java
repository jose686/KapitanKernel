package com.laboratoriodecodigo.KapitanKernel.servicios;

import com.laboratoriodecodigo.KapitanKernel.modelo.Bloques_Post;
import com.laboratoriodecodigo.KapitanKernel.modelo.Posts;

import java.util.List;
import java.util.Optional;

public interface BloquesPostServicios {


    Bloques_Post guardarBloque(Long idPost, Bloques_Post bloquePost);


    Bloques_Post actualizarBloque(Long idPost, Long idBloque, Bloques_Post bloquePost);


    List<Bloques_Post> listarBloquesDePost(Long idPost);

}
