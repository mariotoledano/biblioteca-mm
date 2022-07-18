package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecursoYaVinculadoException extends RuntimeException{
    public RecursoYaVinculadoException(){
        super("Ya existe un vínculo");
    }
    public RecursoYaVinculadoException(String mensajePersonalizado){
        super(mensajePersonalizado);
    }
    private final String accionRecomendada =
            "Utilice el método PUT si desea sobreescribirlo en caso de que sea una relación de Texto->Autor" +
                    "o Tema->Supertema. " +
                    "Si es una relación Texto->Tema utilice el método POST para crear uno nuevo";
    public String getAccionRecomendada(){
        return this.accionRecomendada;
    }
}
