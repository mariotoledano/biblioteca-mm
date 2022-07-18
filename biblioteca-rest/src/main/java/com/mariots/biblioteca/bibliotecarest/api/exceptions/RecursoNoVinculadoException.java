package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecursoNoVinculadoException extends RuntimeException{
    public RecursoNoVinculadoException(){
       super("No existe ningún vínculo previo");
    }
    public RecursoNoVinculadoException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }
    private final String accionRecomendada =
            "Utilice el método POST si desea crearlo. Si desea eliminarlo no es necesario tomar acción.";
    public String getAccionRecomendada(){
        return this.accionRecomendada;
    }
}
