package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicarRecursoException extends RuntimeException{
    public DuplicarRecursoException(){
        super("Este recurso ya existe, no se puede duplicar");
    }
    public DuplicarRecursoException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }

    private final String accionRecomendada = "Introducir datos nuevos";
    public String getAccionRecomendada(){
        return this.accionRecomendada;
    }
}
