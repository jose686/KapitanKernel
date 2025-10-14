package com.laboratoriodecodigo.KapitanKernel;

import com.laboratoriodecodigo.KapitanKernel.modelo.TiposUsuario;
import com.laboratoriodecodigo.KapitanKernel.modelo.Usuario;
import com.laboratoriodecodigo.KapitanKernel.servicios.TipoUsuarioServicios;
import com.laboratoriodecodigo.KapitanKernel.servicios.UsuarioServicios;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class KapitanKernelApplicationTests {

	@Autowired
	private UsuarioServicios usuarioService;

	@Autowired
	private TipoUsuarioServicios tipoUsuarioService;


	@Test
	void crudCompletoDeUsuarioTest() {

	}



	@Test
	void noEliminarTipoUsuarioConUsuarioAsociadoTest() {

	}
}
