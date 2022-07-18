package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampoEnBlancoException extends RuntimeException{
    public CampoEnBlancoException(String campoVacio){
        super("El campo " + campoVacio + " no puede estar en blanco");
    }
    public CampoEnBlancoException(){
        super("Campo en blanco no valido");
    }

    private final String accionRecomendada = "Introducir datos en el campo en blanco";

    public String getAccionRecomendada(){
        return this.accionRecomendada;
    }
}
