package com.laboratoriodecodigo.KapitanKernel.excepciones;

public class RecursoNoEncontradoException extends RuntimeException {

    // Constructor que acepta un mensaje
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}
