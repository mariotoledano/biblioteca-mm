package com.mariots.biblioteca.bibliotecawebadmin.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseEntityExceptionHandlerPersonalizado extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> respuestaGenericaExcepcion(Exception ex, WebRequest request) {
        ex.getMessage();
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExceptionDesdeFeign.class)
    public final ResponseEntity<ModeloException> respuestaExcepcionDesdeFeing(ExceptionDesdeFeign ex, WebRequest request) {

        ModeloExceptionFeign modeloExceptionDesdeFeing = ex.getModeloExcepcionFeing();
        HttpStatus httpStatusDesdeFeing = ex.getHttpStatus();

        return new ResponseEntity(modeloExceptionDesdeFeing, httpStatusDesdeFeing);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public final ResponseEntity<ModeloException> respuestaRecursoNoEncontrado(RecursoNoEncontradoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CampoEnBlancoException.class)
    public final ResponseEntity<ModeloException> respuestaCampoEnBlancoException(CampoEnBlancoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoYaVinculadoException.class)
    public final ResponseEntity<ModeloException> respuestaRecursoYaVinculadoException(RecursoYaVinculadoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNoVinculadoException.class)
    public final ResponseEntity<ModeloException> respuestaRecursoNoVinculadoException(RecursoNoVinculadoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RepetirVinculoException.class)
    public final ResponseEntity<ModeloException> respuestaRepetirVinculoException(RepetirVinculoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }
}
