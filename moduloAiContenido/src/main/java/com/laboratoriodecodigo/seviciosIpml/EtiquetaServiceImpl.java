package com.laboratoriodecodigo.seviciosIpml;


import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.repositorio.EtiquetaRepository;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.text.Normalizer;
import java.util.Locale;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    @Autowired
    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public Etiqueta guardarEtiqueta(Etiqueta etiqueta) {
        // ⭐ Lógica de negocio para generar el slug si falta ⭐
        if (etiqueta.getSlug() == null || etiqueta.getSlug().isEmpty()) {
            // Generar el slug a partir del nombre antes de guardar
            String slugGenerado = generarSlug(etiqueta.getNombre());
            etiqueta.setSlug(slugGenerado);
        }
        return etiquetaRepository.save(etiqueta);
    }

    private String generarSlug(String input) {
        if (input == null) {
            return "";
        }

        // 1. Convertir a minúsculas
        String slug = input.toLowerCase(Locale.ROOT);

        // 2. Normalizar a NFD y quitar caracteres diacríticos (acentos)
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 3. Reemplazar caracteres no alfanuméricos (ni guiones) por guiones
        slug = slug.replaceAll("[^a-z0-9\\s-]", "");

        // 4. Reemplazar espacios y guiones múltiples por un solo guión
        slug = slug.trim()
                .replaceAll("[\\s-]+", "-");

        return slug;
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

    @Override
    public void eliminarEtiqueta(Long idEtiqueta) {
        etiquetaRepository.deleteById(idEtiqueta);
    }
}