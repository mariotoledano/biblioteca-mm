package com.mariots.biblioteca.bibliotecarest.api.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerPersonalizado extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<Object> respuestaGenericaExcepcion(Exception ex, WebRequest request) {
                 ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada("Se ha producido un error no contemplado")
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String tipoRequerido = ex.getRequiredType().toString();
        String valorIntoducidoErroneo = ex.getValue().toString();
        String tipoValorIntoducido = ex.getValue().getClass().toString();

        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion("El valor introducido debe ser del tipo " + tipoRequerido +
                        ". Usted ha introducido " + valorIntoducidoErroneo +
                        ", un valor de tipo "+ tipoValorIntoducido + ".")
                .accionRecomendada("Intoducir un valor del tipo requerido")
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errores = ex.getBindingResult().getAllErrors();
        List<String> mensajesError = new ArrayList<>();
        for (ObjectError error : errores) {
            String mensajeError = error.getDefaultMessage();
            mensajesError.add(mensajeError);
        }
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(mensajesError.toString())
                .accionRecomendada("Intoducir valores que cumplan con los requisitos")
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public final ResponseEntity<ModeloException> respuestaRecursoNoEncontrado(RecursoNoEncontradoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CampoEnBlancoException.class)
    public final ResponseEntity<ModeloException> respuestaCampoEnBlancoException(CampoEnBlancoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoYaVinculadoException.class)
    public final ResponseEntity<ModeloException> respuestaRecursoYaVinculadoException(RecursoYaVinculadoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNoVinculadoException.class)
    public final ResponseEntity<ModeloException> respuestaRecursoNoVinculadoException(RecursoNoVinculadoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepetirVinculoException.class)
    public final ResponseEntity<ModeloException> respuestaRepetirVinculoException(RepetirVinculoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHayResultadosException.class)
    public final ResponseEntity<ModeloException> respuestaNoHayResultadosException(NoHayResultadosException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.OK);
    }

    @ExceptionHandler(DuplicarRecursoException.class)
    public final ResponseEntity<ModeloException> respuestaDuplicarRecursoException(DuplicarRecursoException ex, WebRequest request){
        ModeloException modeloException = ModeloException.builder()
                .fechaYHora(LocalDateTime.now())
                .informacion(ex.getMessage())
                .accionRecomendada(ex.getAccionRecomendada())
                .detalles(request.getDescription(true))
                .build();
        return new ResponseEntity(modeloException, HttpStatus.CONFLICT);
    }

}
