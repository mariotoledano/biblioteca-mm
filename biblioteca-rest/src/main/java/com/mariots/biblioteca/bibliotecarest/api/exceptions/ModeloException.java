package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ModeloException{

    private LocalDateTime fechaYHora;
    private String informacion;
    private String accionRecomendada;
    private String detalles;
}
