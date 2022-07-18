package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(){
        super("Recurso no encontrado");
    }
    public RecursoNoEncontradoException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }
    private final String accionRecomendada = "Intoducir un identificador que coincida con uno existente";
    public String getAccionRecomendada(){
        return this.accionRecomendada;
    }
}
