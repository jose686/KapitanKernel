package com.laboratoriodecodigo.seviciosIpml;


import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.repositorio.EtiquetaRepository;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    @Autowired
    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public Etiqueta guardarEtiqueta(Etiqueta etiqueta) {
        // Lógica de negocio: asegura que el slug se genera correctamente si no existe
        if (etiqueta.getSlug() == null || etiqueta.getSlug().isEmpty()) {
            // Implementar lógica de generación de slug aquí
            // Ejemplo: etiqueta.setSlug(generarSlug(etiqueta.getNombre()));
        }
        return etiquetaRepository.save(etiqueta);
    }

    @Override
    public Etiqueta buscarPorId(Long idEtiqueta) {
        return etiquetaRepository.findById(idEtiqueta).orElse(null);
    }

    @Override
    public Etiqueta buscarPorNombre(String nombre) {
        return etiquetaRepository.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<Etiqueta> obtenerTodas() {
        return etiquetaRepository.findAll();
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.buscarPorNombre(nombre) != null;
    }
}