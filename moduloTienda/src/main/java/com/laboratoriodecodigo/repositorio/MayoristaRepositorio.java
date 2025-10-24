package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MayoristaRepositorio extends JpaRepository<Mayorista, Long> {
}