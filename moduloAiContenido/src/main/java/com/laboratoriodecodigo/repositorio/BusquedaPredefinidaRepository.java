package com.laboratoriodecodigo.repositorio;


import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusquedaPredefinidaRepository extends JpaRepository<BusquedaPredefinida, Long> {


    List<BusquedaPredefinida> findByActivaTrue();

    List<BusquedaPredefinida> findByActivaTrueAndFrecuenciaMinutos(Integer minutos);
}