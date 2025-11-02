package com.laboratoriodecodigo.serviciosImpl;


import com.laboratoriodecodigo.controlador.RecursoNoEncontradoException;
import com.laboratoriodecodigo.modelo.blog.ActualizarPerfilDto;
import com.laboratoriodecodigo.modelo.usuarios.TiposUsuario;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.repositorio.UsuarioRepository;
import com.laboratoriodecodigo.servicios.TipoUsuarioServicios;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.security.crypto.password.PasswordEncoder; // ⬅️ NUEVA IMPORTACIÓN

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiciosIpml implements UsuarioServicios {


    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioServicios tipoUsuarioService;
    private final PasswordEncoder passwordEncoder; // ⬅️ NUEVA PROPIEDAD

    @Autowired
    public UsuarioServiciosIpml(
            UsuarioRepository usuarioRepository,
            TipoUsuarioServicios tipoUsuarioService,
            PasswordEncoder passwordEncoder // ⬅️ INYECTAR PasswordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioService = tipoUsuarioService;
        this.passwordEncoder = passwordEncoder; // ⬅️ ASIGNAR
    }


    public Usuario guardarUsuario(Usuario usuario, Long idTipoUsuario) {

        TiposUsuario tipo = tipoUsuarioService.obtenerTipoUsuarioPorId(idTipoUsuario);
        usuario.setTipoUsuario(tipo);
        String contrasenaPlana = usuario.getContrasena_hash();

        String contrasenaCifrada = passwordEncoder.encode(contrasenaPlana);
        usuario.setContrasena_hash(contrasenaCifrada);

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
            // Nota: El hash de la contraseña no se toca en una actualización normal.
            return usuarioRepository.save(usuarioExistente);

        }else{
            throw new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {

            throw new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
        return usuario;
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

    @Override
    public void cambiarPassword(String nombreDeUsuario, String currentPassword, String newPassword) {

        // 1. Buscar el usuario por su nombre de login (usando el método existente)
        Usuario usuario = obtenerUsuarioPorNombre(nombreDeUsuario);

        if (usuario == null) {
            throw new RecursoNoEncontradoException("Usuario no encontrado.");
        }

        // 2. Verificar la contraseña actual (CRÍTICO: usando passwordEncoder.matches)
        if (!passwordEncoder.matches(currentPassword, usuario.getContrasena_hash())) {
            throw new RuntimeException("La contraseña actual es incorrecta.");
        }

        // 3. Codificar la nueva contraseña y establecerla
        String nuevaContrasenaCifrada = passwordEncoder.encode(newPassword);
        usuario.setContrasena_hash(nuevaContrasenaCifrada);

        // 4. Guardar los cambios
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarPerfil(String nombreActual, ActualizarPerfilDto request) {

        // 1. Buscar el usuario
        Usuario usuario = obtenerUsuarioPorNombre(nombreActual);

        if (usuario == null) {
            throw new RecursoNoEncontradoException("Usuario no encontrado.");
        }
        if (request.getNuevoNombre() != null && !request.getNuevoNombre().equals(nombreActual)) {
            //
            if (usuarioRepository.findByNombreUsuario(request.getNuevoNombre()) != null) {
                throw new RuntimeException("El nombre de usuario '" + request.getNuevoNombre() + "' ya está en uso.");
            }
            usuario.setNombreUsuario(request.getNuevoNombre());
        }

        if (request.getNuevoCorreo() != null) {

            usuario.setCorreo(request.getNuevoCorreo());
        }

        usuarioRepository.save(usuario);
    }
    @Override
    public void resetPasswordByAdmin(Long idUsuario, String nuevaContrasena) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isEmpty()) {
            throw new RecursoNoEncontradoException("Usuario con ID " + idUsuario + " no encontrado.");
        }

        Usuario usuario = usuarioOptional.get();

        // ⭐ 1. Codificar la nueva contraseña
        String nuevaContrasenaCifrada = passwordEncoder.encode(nuevaContrasena);

        // ⭐ 2. Forzar el cambio de la contraseña
        usuario.setContrasena_hash(nuevaContrasenaCifrada);

        // 3. Guardar el usuario
        usuarioRepository.save(usuario);
    }

}