package com.laboratoriodecodigo.repositorio;



import com.laboratoriodecodigo.modelo.finanzas.IngresoTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoTiendaRepositorio extends JpaRepository<IngresoTienda, Long> {
}