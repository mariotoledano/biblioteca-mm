package com.mariots.biblioteca.bibliotecawebadmin.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExceptionDesdeFeign extends RuntimeException{

    private ModeloExceptionFeign modeloExcepcionFeing;
    private HttpStatus httpStatus;


}
