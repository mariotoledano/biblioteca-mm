package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NoHayResultadosException extends RuntimeException {
    public NoHayResultadosException(){
        super("No existen recursos de este tipo actualmente");
    }
    public NoHayResultadosException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }
    private final String accionRecomendada = "Buscar otro recurso o añadir nuevos registros";
    public String getAccionRecomendada(){
        return this.accionRecomendada;
    }
}
