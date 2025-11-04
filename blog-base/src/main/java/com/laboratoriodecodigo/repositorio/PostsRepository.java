package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.blog.PostStatus;
import com.laboratoriodecodigo.modelo.blog.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {


    List<Posts> findByEstado(PostStatus estado);


    @Query("SELECT p FROM Posts p " +
            "LEFT JOIN FETCH p.bloquesDeContenido b " +
            "LEFT JOIN FETCH b.imagen " +
            "WHERE p.idTipo = :id")
    Optional<Posts> findByIdWithFullContent(@Param("id") Long id);
}
