package com.laboratoriodecodigo.seviciosIpml;

import com.laboratoriodecodigo.modelo.iaContenido.EstadoProcesamientoEnum;
import com.laboratoriodecodigo.modelo.iaContenido.NoticiaExterna;
import com.laboratoriodecodigo.modelodto.ArticleDTO;
import com.laboratoriodecodigo.repositorio.NoticiaExternaRepositorio;
import com.laboratoriodecodigo.servicios.GNewsApiService;
import com.laboratoriodecodigo.servicios.INoticiasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.UUID;


@Service
public class NoticiaExternaServiceImpl implements INoticiasService {

    private final NoticiaExternaRepositorio repository;
    private final GNewsApiService gnewsApiService;

    @Autowired
    public NoticiaExternaServiceImpl(NoticiaExternaRepositorio repository, GNewsApiService gnewsApiService) {
        this.repository = repository;
        this.gnewsApiService = gnewsApiService;
    }

    @Override
    public int sincronizarNoticiasDesdeAPI(String categoria, int limit) {

        List<ArticleDTO> articulosRecibidos = gnewsApiService.buscarNoticias(categoria, limit);
        if (articulosRecibidos == null || articulosRecibidos.isEmpty()) {
            return 0;
        }

        int articulosGuardados = 0;

        for (ArticleDTO dto : articulosRecibidos) {
            String idNoticia = generarIdExterno(dto.getUrl());
            if (repository.findByIdNoticia(idNoticia).isEmpty()) {
                try {
                    NoticiaExterna nuevaNoticia = convertirDtoAEntidad(dto, idNoticia);
                    repository.save(nuevaNoticia);
                    articulosGuardados++;
                } catch (DateTimeParseException e) {
                    System.err.println("CRÍTICO: Error al parsear la fecha de la noticia con URL: " + dto.getUrl() + ". Detalle: " + e.getMessage());
                } catch (Exception e) {
                    // Capturar cualquier otro error de guardado o mapeo
                    System.err.println("CRÍTICO: Error general al procesar la noticia con URL: " + dto.getUrl() + ". Detalle: " + e.getMessage());
                }
            }
        }
        return articulosGuardados;
    }

    @Override
    public Optional<NoticiaExterna> findByIdNoticia(String idNoticiaHash) {
        return repository.findByIdNoticia(idNoticiaHash);
    }

    @Override
    public List<NoticiaExterna> findLatest(int count) {

        Pageable pageable = PageRequest.of(
                0,
                count,
                Sort.by("publishedAt").descending()
        );

        return repository.findAllBy(pageable);

    }

    @Override
    public List<NoticiaExterna> findLatestFiltered(int count, String estado, String idioma, String q) {

        Pageable pageable = PageRequest.of(0, count);

        EstadoProcesamientoEnum estadoEnum = null;
        if (estado != null && !estado.equalsIgnoreCase("ALL")) {
            try {
                // Convierte el String (ej: "APROBADO") a la constante Enum
                estadoEnum = EstadoProcesamientoEnum.valueOf(estado.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Opcional: Manejar un estado inválido (ej: devolver vacío o loggear error)
                System.err.println("Estado de procesamiento no válido: " + estado);
                return Collections.emptyList();
            }
        }

        // 🌟 Llama al Repositorio con el Enum
        return repository.findFiltered(pageable, estadoEnum, idioma, q);
    }

    @Override
    @Transactional
    public void cambiarEstado(String idNoticia, EstadoProcesamientoEnum nuevoEstado) {

        NoticiaExterna noticia = repository.findByIdNoticia(idNoticia)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada con ID: " + idNoticia));
        noticia.setEstadoProcesamiento(nuevoEstado);

        repository.save(noticia);

        System.out.println("Noticia " + idNoticia + " cambiada a estado: " + nuevoEstado);
    }


    /**********************************************************/
    private String generarIdExterno(String url) {
        // Usa la URL para crear un hash consistente (el ID de tu DB)
        return UUID.nameUUIDFromBytes(url.getBytes()).toString();
    }

    private NoticiaExterna convertirDtoAEntidad(ArticleDTO dto, String externalId) throws DateTimeParseException {
        // 1. Parseo de fecha desde el formato de GNews
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dto.getPublishedAt());
        LocalDateTime publicadoEn = zonedDateTime.toLocalDateTime();

        // 2. Construcción de la Entidad
        return NoticiaExterna.builder()
                .idNoticia(externalId)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .url(dto.getUrl())
                .image(dto.getImage())
                .publishedAt(publicadoEn)

                // Asignación de metadata
                .idioma("es")
                .sourceName(dto.getSource().getName())
                .fechaImportacion(LocalDateTime.now())

                // Estado inicial para el nuevo flujo de trabajo
                .estadoProcesamiento(EstadoProcesamientoEnum.PENDIENTE)
                .build();
    }


}