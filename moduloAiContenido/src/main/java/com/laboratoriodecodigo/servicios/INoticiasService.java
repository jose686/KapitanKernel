package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.iaContenido.EstadoProcesamientoEnum;
import com.laboratoriodecodigo.modelo.iaContenido.NoticiaExterna;
import com.laboratoriodecodigo.modelodto.ArticleDTO;

import java.util.List;
import java.util.Optional;

public interface INoticiasService {

    int sincronizarNoticiasDesdeAPI(String categoria, int limit);
    Optional<NoticiaExterna> findByIdNoticia(String idNoticiaHash);
    List<NoticiaExterna> findLatest(int count);

    List<NoticiaExterna> findLatestFiltered(int count, String estado, String idioma, String q);

    void cambiarEstado(String idNoticia, EstadoProcesamientoEnum estadoProcesamientoEnum);
}
