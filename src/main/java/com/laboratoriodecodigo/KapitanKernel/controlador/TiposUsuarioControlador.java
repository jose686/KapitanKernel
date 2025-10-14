package com.laboratoriodecodigo.KapitanKernel.controlador;


import com.laboratoriodecodigo.KapitanKernel.excepciones.RecursoNoEncontradoException;
import com.laboratoriodecodigo.KapitanKernel.modelo.TiposUsuario;
import com.laboratoriodecodigo.KapitanKernel.servicios.TipoUsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiposUsuario")
@CrossOrigin(origins = "*")
public class TiposUsuarioControlador {

    private final TipoUsuarioServicios tipoUsuarioServicios;

    @Autowired
    public TiposUsuarioControlador(TipoUsuarioServicios tipoUsuarioServicios) {
        this.tipoUsuarioServicios = tipoUsuarioServicios;
    }



    @PostMapping
    public ResponseEntity<TiposUsuario> crearTipoUsuario(@RequestBody TiposUsuario tipoUsuario) {
        TiposUsuario nuevoTipo = tipoUsuarioServicios.guardarTipoUsuario(tipoUsuario);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<TiposUsuario>> obtenerTodosLosTiposDeUsuario (){
       List<TiposUsuario> tiposUsuarios =tipoUsuarioServicios.listarTiposUsuario();

       return ResponseEntity.ok(tiposUsuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoDeUsuario (@PathVariable Long id){
        try{
            tipoUsuarioServicios.eliminarTipoUsuario(id);
            return ResponseEntity.noContent().build();
        }catch (RecursoNoEncontradoException e){
            return ResponseEntity.notFound().build();
        }


    }


}
