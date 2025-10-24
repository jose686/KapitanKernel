package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.blog.Bloques_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloquesPostRepository extends JpaRepository<Bloques_Post,Long> {
}
