package com.laboratoriodecodigo.servicios;

import com.laboratoriodecodigo.modelo.iaContenido.EstadoProcesamientoEnum;
import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.modelo.iaContenido.NoticiaExterna;
import com.laboratoriodecodigo.modelodto.ArticleDTO;

import java.util.List;
import java.util.Optional;

public interface INoticiasService {

    NoticiaExterna guardarNoticiaYAsignarEtiqueta(NoticiaExterna noticia, Etiqueta etiqueta);
    int sincronizarYGuardar(String query, int limit, Etiqueta etiqueta);
    int sincronizarNoticiasDesdeAPI(String categoria, int limit);
    Optional<NoticiaExterna> findByIdNoticia(String idNoticiaHash);
    List<NoticiaExterna> findLatest(int count);
    List<NoticiaExterna> buscarPorEtiqueta(String nombreEtiqueta);
    List<NoticiaExterna> findLatestFiltered(int count, String estado, String idioma, String q);

    void cambiarEstado(String idNoticia, EstadoProcesamientoEnum estadoProcesamientoEnum);

    List<NoticiaExterna> buscarPorEtiquetaYEstado(String nombreEtiqueta, String estado);
}
