package com.laboratoriodecodigo.repositorio;

import com.laboratoriodecodigo.modelo.Mayorista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MayoristaRepositorio extends JpaRepository<Mayorista, Long> {
}