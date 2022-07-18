package com.mariots.biblioteca.bibliotecawebadmin.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModeloExceptionFeign {
    private String fechaYHora;
    private String informacion;
    private String accionRecomendada;
    private String detalles;
}
