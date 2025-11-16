package com.laboratoriodecodigo.configuracion;

import com.laboratoriodecodigo.modelo.usuarios.TiposUsuario;
import com.laboratoriodecodigo.modelo.usuarios.Usuario;
import com.laboratoriodecodigo.servicios.TipoUsuarioServicios;
import com.laboratoriodecodigo.servicios.UsuarioServicios;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements CommandLineRunner {

    private final UsuarioServicios usuarioServicios;
    private final TipoUsuarioServicios tipoUsuarioServicios;

    public SetupDataLoader(UsuarioServicios usuarioServicios, TipoUsuarioServicios tipoUsuarioServicios) {
        this.usuarioServicios = usuarioServicios;
        this.tipoUsuarioServicios = tipoUsuarioServicios;
    }

    @Override
    public void run(String... args) throws Exception {
        crearTiposDeUsuarioSiNoExisten();
        crearUsuarioAdminSiNoExiste();
    }

    private void crearTiposDeUsuarioSiNoExisten() {

        final String ADMIN_ROLE_NAME = "ADMIN";
        if (tipoUsuarioServicios.obtenerTipoUsuarioPorNombre(ADMIN_ROLE_NAME) == null) {
            TiposUsuario adminRole = new TiposUsuario();
            adminRole.setNombreTipo(ADMIN_ROLE_NAME);
            tipoUsuarioServicios.guardarTipoUsuario(adminRole);
            System.out.println("Tipo de Usuario creado: ADMIN");
        }

        // ⭐ 2. CREAR ROL/TIPO 'USUARIO' (ID 2L asumido)
        final String USER_ROLE_NAME = "USUARIO";
        if (tipoUsuarioServicios.obtenerTipoUsuarioPorNombre(USER_ROLE_NAME) == null) {
            TiposUsuario userRole = new TiposUsuario();
            // userRole.setId(2L); // No asignar IDs manualmente
            userRole.setNombreTipo(USER_ROLE_NAME);
            tipoUsuarioServicios.guardarTipoUsuario(userRole);
            System.out.println("Tipo de Usuario creado: USUARIO");
        }
    }

    private void crearUsuarioAdminSiNoExiste() {
        final String ADMIN_USERNAME = "admin@kapitankernel.com";
        final String ADMIN_PASSWORD = "1234";
        TiposUsuario adminTipo = tipoUsuarioServicios.obtenerTipoUsuarioPorNombre("ADMIN");
        Long ADMIN_TIPO_ID = adminTipo.getIdTipo();

        if (usuarioServicios.obtenerUsuarioPorCorreo(ADMIN_USERNAME).isEmpty()) {

            Usuario admin = new Usuario();
            admin.setNombreUsuario("Admin");
            admin.setCorreo(ADMIN_USERNAME);
            admin.setContrasena_hash(ADMIN_PASSWORD);

            // Usamos el ID del tipo que acabamos de asegurar que existe
            usuarioServicios.guardarUsuario(admin, ADMIN_TIPO_ID);
            System.out.println("Usuario Admin creado con éxito: " + ADMIN_USERNAME);

        } else {
            System.out.println("El usuario admin ya existe.");
        }
    }
}