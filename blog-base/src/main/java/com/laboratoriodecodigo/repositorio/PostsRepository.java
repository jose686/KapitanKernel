package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {



}
