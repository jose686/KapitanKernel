package com.laboratoriodecodigo.seviciosIpml;




import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.repositorio.BusquedaPredefinidaRepository;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusquedaPredefinidaServiceImpl implements BusquedaPredefinidaService {

    private final BusquedaPredefinidaRepository busquedaRepository;

    @Autowired
    public BusquedaPredefinidaServiceImpl(BusquedaPredefinidaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    @Override
    public BusquedaPredefinida guardarBusqueda(BusquedaPredefinida busqueda) {
        return busquedaRepository.save(busqueda);
    }

    @Override
    public List<BusquedaPredefinida> obtenerBusquedasActivas() {
        return busquedaRepository.findByActivaTrue();
    }

    @Override
    public List<BusquedaPredefinida> obtenerTodas() {
        return busquedaRepository.findAll();
    }

    @Override
    public void desactivarBusqueda(Long idBusqueda) {
        busquedaRepository.findById(idBusqueda).ifPresent(busqueda -> {
            busqueda.setActiva(false);
            busquedaRepository.save(busqueda);
        });
    }

    @Override
    public void actualizarUltimaEjecucion(BusquedaPredefinida busqueda) {
        // Aseguramos que el objeto está en la sesión antes de actualizar
        busqueda.setUltimaEjecucion(LocalDateTime.now());
        busquedaRepository.save(busqueda);
    }
}