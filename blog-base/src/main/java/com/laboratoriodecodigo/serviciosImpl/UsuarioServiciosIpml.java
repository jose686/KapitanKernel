package com.laboratoriodecodigo.serviciosImpl;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.TiposUsuario;
import com.laboratoriodecodigo.modelo.Usuario;
import com.laboratoriodecodigo.repositorio.UsuarioRepository;
import com.laboratoriodecodigo.servicios.TipoUsuarioServicios;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiciosIpml implements UsuarioServicios {


    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioServicios tipoUsuarioService;


    @Autowired
    public UsuarioServiciosIpml(UsuarioRepository usuarioRepository, TipoUsuarioServicios tipoUsuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioService = tipoUsuarioService;
    }



    @Override
    public Usuario guardarUsuario(Usuario usuario, String nombreTipoUsuario) {
        TiposUsuario tipo = tipoUsuarioService.obtenerTipoUsuarioPorNombre(nombreTipoUsuario);
        usuario.setTipoUsuario(tipo);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioExistente =usuarioOptional.get();
            usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            return usuarioRepository.save(usuarioExistente);

        }else{
            throw new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        if (usuarioRepository.existsByTipoUsuarioIdTipo(id)) {
            // Lanza la excepción que el test está esperando
            throw new DataIntegrityViolationException("No se puede eliminar el tipo de usuario, ya que hay usuarios asociados a él.");
        }
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Tipo de usuario con ID " + id + " no encontrado.");
        }
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return usuarioRepository.findByNombreUsuario(nombre);
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }


}
