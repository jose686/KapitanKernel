package com.laboratoriodecodigo.KapitanKernel.serviciosImpl;

import com.laboratoriodecodigo.KapitanKernel.excepciones.RecursoNoEncontradoException;
import com.laboratoriodecodigo.KapitanKernel.modelo.Categorias;
import com.laboratoriodecodigo.KapitanKernel.repositorio.CategoriasRepository;
import com.laboratoriodecodigo.KapitanKernel.servicios.CategoriasServicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoriasServiciosIpml implements CategoriasServicios {

    private final CategoriasRepository categoriasRepository;

    public CategoriasServiciosIpml(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    @Override
    public Categorias guardarCategoria(Categorias categoria) {
        return categoriasRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {

        if (!categoriasRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Categoría con ID " + id + " no encontrada.");
        }

        categoriasRepository.deleteById(id);
    }

    @Override
    public Optional<Categorias> obtenerCategoriaPorId(Long id) {
        return categoriasRepository.findById(id);
    }


    @Override
    public Categorias obtenerCategoriaPorNombre(String nombre) {
        return null;
    }

    @Override
    public List<Categorias> listarCategorias() {
        return categoriasRepository.findAll();
    }
}
