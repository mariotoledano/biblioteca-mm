package com.mariots.biblioteca.bibliotecawebadmin.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecursoYaVinculadoException extends RuntimeException{
    public RecursoYaVinculadoException(){
        super("Ya existe un vínculo. Utilice PUT para sobreescribir");
    }
    public RecursoYaVinculadoException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }
}
