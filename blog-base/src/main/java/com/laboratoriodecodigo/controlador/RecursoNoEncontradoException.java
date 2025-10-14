package com.laboratoriodecodigo.controlador;

public class RecursoNoEncontradoException extends RuntimeException {

    // Constructor que acepta un mensaje
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}
