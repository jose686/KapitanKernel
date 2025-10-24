package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.iaContenido.EstadoProcesamientoEnum;
import com.laboratoriodecodigo.modelo.iaContenido.NoticiaExterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticiaExternaRepositorio extends JpaRepository<NoticiaExterna, String> {

    Optional<NoticiaExterna> findByIdNoticia(String idNoticia);

    List<NoticiaExterna> findAllBy(Pageable pageable);




        @Query("SELECT n FROM NoticiaExterna n " +
                // 💡 CAMBIO CLAVE: Cambiar :estado a :estadoEnum
                "WHERE (:estadoEnum IS NULL OR n.estadoProcesamiento = :estadoEnum) " +
                "AND (:idioma IS NULL OR n.idioma = :idioma) " +
                "AND (:q IS NULL OR n.title LIKE %:q% OR n.description LIKE %:q%) " +
                "ORDER BY n.publishedAt DESC")
        List<NoticiaExterna> findFiltered(
                Pageable pageable,
                @Param("estadoEnum") EstadoProcesamientoEnum estadoEnum,
                @Param("idioma") String idioma,
                @Param("q") String q);

}


