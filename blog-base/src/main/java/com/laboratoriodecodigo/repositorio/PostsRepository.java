package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.blog.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {



}
