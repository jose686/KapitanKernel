package com.laboratoriodecodigo.KapitanKernel.serviciosImpl;

import com.laboratoriodecodigo.KapitanKernel.excepciones.RecursoNoEncontradoException;
import com.laboratoriodecodigo.KapitanKernel.modelo.TiposUsuario;
import com.laboratoriodecodigo.KapitanKernel.modelo.Usuario;
import com.laboratoriodecodigo.KapitanKernel.repositorio.TiposUsuarioRepository;
import com.laboratoriodecodigo.KapitanKernel.repositorio.UsuarioRepository;
import com.laboratoriodecodigo.KapitanKernel.servicios.TipoUsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TipoUsuarioServiciosIpml implements TipoUsuarioServicios {


    private final TiposUsuarioRepository tiposUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TipoUsuarioServiciosIpml(TiposUsuarioRepository tiposUsuarioRepository, UsuarioRepository usuarioRepository) {
        this.tiposUsuarioRepository = tiposUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }




    @Override
    public TiposUsuario guardarTipoUsuario(TiposUsuario tiposUsuario) {
        return tiposUsuarioRepository.save(tiposUsuario);
    }

    @Override
    public void eliminarTipoUsuario(Long id) {
        if (usuarioRepository.existsByTipoUsuarioIdTipo(id)) {
            // Lanza la excepción que el test está esperando
            throw new DataIntegrityViolationException("No se puede eliminar el tipo de usuario, ya que hay usuarios asociados a él.");
        }
        if (!tiposUsuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Tipo de usuario con ID " + id + " no encontrado.");
        }
        tiposUsuarioRepository.deleteById(id);;
    }

    @Override
    public TiposUsuario obtenerTipoUsuarioPorNombre(String nombre) {
        return tiposUsuarioRepository.findByNombreTipo(nombre);
    }

    @Override
    public List<TiposUsuario> listarTiposUsuario() {
        return tiposUsuarioRepository.findAll();
    }

    // En TipoUsuarioServicios.java

    public TiposUsuario obtenerTipoUsuarioPorId(Long id) {
        return tiposUsuarioRepository.findById(id)
        .orElseThrow(() -> new RecursoNoEncontradoException("Tipo de usuario no encontrado"));
    }




}
