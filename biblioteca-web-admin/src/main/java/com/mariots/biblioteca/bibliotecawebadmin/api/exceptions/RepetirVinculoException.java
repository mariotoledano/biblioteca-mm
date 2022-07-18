package com.mariots.biblioteca.bibliotecawebadmin.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RepetirVinculoException extends RuntimeException{
    public RepetirVinculoException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }
    public RepetirVinculoException(){
        super("Este mismo vínculo ya existía previamente");
    }
}
