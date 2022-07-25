package com.mariots.biblioteca.bibliotecarest.core.controller;

import com.mariots.biblioteca.bibliotecarest.api.hateoas.Hateoas;
import com.mariots.biblioteca.bibliotecarest.api.hateoas.indice.Indice;
import com.mariots.biblioteca.bibliotecarest.api.mapper.Mapper;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.AutorDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.SupertemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TextoDto;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.AutorNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.SupertemaNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.TemaNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.TextoNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TemaSupertema;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TextoAutor;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TextoTema;
import com.mariots.biblioteca.bibliotecarest.core.service.ServiceBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ControllerBibliotecaRest {

    @Autowired
    ServiceBiblioteca service;
    @Autowired
    Mapper mapper;
    @Autowired
    Hateoas hateoas;


    @GetMapping(value = "/prueba")
    public List<Link> prueba(){
        Link linkTodosAutores = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarAutores()).withRel("Recuperar todos los Autores");
        Link linkTodosTextos = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTextos()).withRel("Recuperar todos los Textos");
        Link linkTodosTemas = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTemas()).withRel("Recuperar todos los Temas");
        Link linkTodosSupertemas = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarSupertemas()).withRel("Recuperar todos los Supertemas");
        List<Link> linksTodosRecursos = Arrays.asList(linkTodosAutores,linkTodosTextos, linkTodosTemas, linkTodosSupertemas);
        return linksTodosRecursos;
    }
//GET indice
    @GetMapping(value = "/indice")
    public ResponseEntity irAlIndice(){
        Indice indice = Hateoas.recuperarIndice();
        return new ResponseEntity<Indice>(indice,HttpStatus.OK);
    }

//GET resource -- Recuperar todos los resources de un tipo
    @GetMapping(value = "/autores")
    public ResponseEntity recuperarAutores() {
        List<AutorDto> autores = service.recuperarAutores();
        return new ResponseEntity<List<AutorDto>>(autores, HttpStatus.FOUND);
    }

    @GetMapping(value = "/textos")
    public ResponseEntity recuperarTextos() {
        List<TextoDto> textos = service.recuperarTextos();
        return new ResponseEntity<List<TextoDto>>(textos, HttpStatus.FOUND);
    }

    @GetMapping(value = "/temas")
    public ResponseEntity recuperarTemas() {
        List<TemaDto> temas = service.recuperarTemas();
        return new ResponseEntity<List<TemaDto>>(temas, HttpStatus.FOUND);
    }

    @GetMapping(value = "/supertemas")
    public ResponseEntity recuperarSupertemas() {
        List<SupertemaDto> supertemas = service.recuperarSupertemas();
        return new ResponseEntity<List<SupertemaDto>>(supertemas, HttpStatus.FOUND);
    }

//GET resource/{id} --> Recuperar recurso por id

    @GetMapping(value = "/autores/{id}")
    public ResponseEntity recuperarAutorPorId(@PathVariable @Valid int id) {
        AutorDto autor = service.recuperarAutorPorId(id);
        return new ResponseEntity<AutorDto>(autor, HttpStatus.FOUND);
    }

    @GetMapping(value = "/textos/{id}")
    public ResponseEntity recuperarTextoPorId(@PathVariable int id) {
        TextoDto texto = service.recuperarTextoPorId(id);
        return new ResponseEntity<TextoDto>(texto, HttpStatus.FOUND);
    }

    @GetMapping(value = "/temas/{id}")
    public ResponseEntity recuperarTemaPorId(@PathVariable int id) {
        TemaDto tema = service.recuperarTemaPorId(id);
        return new ResponseEntity<TemaDto>(tema, HttpStatus.FOUND);
    }

    @GetMapping(value = "/supertemas/{id}")
    public ResponseEntity recuperarSupertemaPorId(@PathVariable int id) {
        SupertemaDto supertema = service.recuperarSupertemaPorId(id);
        return new ResponseEntity<SupertemaDto>(supertema, HttpStatus.FOUND);
    }

//GET resource/nombre/{nombre} --> Recuperar recurso por nombre

    @GetMapping(value = "/autores/nombre/{nombreAutor}")
    public ResponseEntity recuperarAutorPorNombre(@PathVariable String nombreAutor) {
        AutorDto autor = service.recuperarAutorPorNombre(nombreAutor);
        return new ResponseEntity<AutorDto>(autor, HttpStatus.FOUND);
    }

    @GetMapping(value = "/temas/nombre/{nombreTema}")
    public ResponseEntity recuperarTemaPorNombre(@PathVariable String nombreTema) {
        TemaDto tema = service.recuperarTemaPorNombre(nombreTema);
        return new ResponseEntity<TemaDto>(tema, HttpStatus.FOUND);
    }

    @GetMapping(value = "/supertemas/nombre/{nombreSupertema}")
    public ResponseEntity recuperarSupertemaPorNombre(@PathVariable String nombreSupertema) {
        SupertemaDto supertema = service.recuperarSupertemaPorNombre(nombreSupertema);
        return new ResponseEntity<SupertemaDto>(supertema, HttpStatus.FOUND);
    }

//GET resource/resource/{id} --> texto por autor, texto por tema, texto por supertema, tema por supertema

    @GetMapping(value="/textos/autores/{idAutor}")
    public ResponseEntity recuperarTextosPorAutor(@PathVariable int idAutor){
        List<TextoDto> textos = service.recuperarTextosPorAutor(idAutor);
        return new ResponseEntity<List<TextoDto>>(textos, HttpStatus.FOUND);
    }
    @GetMapping(value="/textos/temas/{idTema}")
    public ResponseEntity recuperarTextosPorTema(@PathVariable int idTema){
        List<TextoDto> textos = service.recuperarTextosPorTema(idTema);
        return new ResponseEntity<List<TextoDto>>(textos, HttpStatus.FOUND);
    }
    @GetMapping(value="/textos/supertemas/{idSupertema}")
    public ResponseEntity recuperarTextosPorSupertema(@PathVariable int idSupertema){
        List<TextoDto> textos = service.recuperarTextosPorSupertema(idSupertema);
        return new ResponseEntity<List<TextoDto>>(textos, HttpStatus.FOUND);
    }
    @GetMapping(value="/temas/supertemas/{idSupertema}")
    public ResponseEntity recuperarTemasPorSupertema(@PathVariable int idSupertema){
        List<TemaDto> temas = service.recuperarTemasPorSupertema(idSupertema);
        return new ResponseEntity<List<TemaDto>>(temas, HttpStatus.FOUND);
    }

//POST resource --> Creación nuevo resource

    @PostMapping(value = "/autores")
    public ResponseEntity registrarNuevoAutor(@RequestBody AutorNuevo autor) {
        AutorDto autorGuardado = service.guardarAutor(autor);
        return new ResponseEntity<AutorDto>(autorGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/temas")
    public ResponseEntity registrarNuevoTema(@RequestBody TemaNuevo tema) {
        TemaDto temaGuardado = service.guardarTema(tema);
        return new ResponseEntity<TemaDto>(temaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/supertemas")
    public ResponseEntity registrarNuevoSupertema(@RequestBody SupertemaNuevo supertema) {
        SupertemaDto supertemaGuardado = service.guardarSupertema(supertema);
        return new ResponseEntity<SupertemaDto>(supertemaGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos")
    public ResponseEntity registrarNuevoTexto(@RequestBody @Validated TextoNuevo texto) {
        TextoDto textoGuardado = service.guardarTexto(texto);
        return new ResponseEntity<TextoDto>(textoGuardado, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos/autores/{idAutor}/temas/{idTema}")
    public ResponseEntity registrarNuevoTextoVinculadoPorPath
            (@RequestBody TextoNuevo texto, @PathVariable int idAutor, @PathVariable int idTema){
        TextoDto textoGuardado = service.guardarTextoDesdePath(texto, idAutor, idTema);
        return new ResponseEntity<TextoDto>(textoGuardado, HttpStatus.CREATED);
    }

//POST resource/{id}/resource/{id} -->vincular recursos (actualizar vinculos existentes con PUT)

    @PostMapping(value = "/temas/{idTema}/supertemas/{idSupertema}")
    public ResponseEntity vincularTemaSupertema(@PathVariable int idTema, @PathVariable int idSupertema) {
        TemaSupertema temaSupertema = service.vincularTemaSupertema(idTema, idSupertema);
        return new ResponseEntity<TemaSupertema>(temaSupertema, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos/{idTexto}/temas/{idTema}")
    public ResponseEntity vincularTextoTema(@PathVariable int idTexto, @PathVariable int idTema) {
        TextoTema textoTema = service.vincularTextoTema(idTexto, idTema);
        return new ResponseEntity<TextoTema>(textoTema, HttpStatus.CREATED);
    }

    @PostMapping(value = "/textos/{idTexto}/autores/{idAutor}")
    public ResponseEntity vincularTextoAutor(@PathVariable int idTexto, @PathVariable int idAutor) {
        TextoAutor textoAutor = service.vincularTextoAutor(idTexto, idAutor);
        return new ResponseEntity<TextoAutor>(textoAutor, HttpStatus.CREATED);
    }

//PUT resource/{id}/resource/{id} --> sobreescribir vínculos existentes

    @PutMapping(value = "/temas/{idTema}/supertemas/{idSupertema}")
    public ResponseEntity sobreescribirVinculoTemaSupertema(@PathVariable int idTema, @PathVariable int idSupertema) {
        TemaSupertema temaSupertema = service.sobreescribirVinculoTemaSupertema(idTema, idSupertema);
        return new ResponseEntity<TemaSupertema>(temaSupertema, HttpStatus.CREATED);
    }

    @PutMapping(value = "/textos/{idTexto}/autores/{idAutor}")
    public ResponseEntity sobreescribirVinculoTextoAutor(@PathVariable int idTexto, @PathVariable int idAutor) {
        TextoAutor textoAutor = service.sobreescribirVinculoTextoAutor(idTexto, idAutor);
        return new ResponseEntity<TextoAutor>(textoAutor, HttpStatus.CREATED);
    }

//DELETE resource/{id}/resource/{id} --> Eliminar vínculos entre recursos por id

    @DeleteMapping(value = "/temas/{idTema}/supertemas/{idSupertema}")
    public ResponseEntity desvincularTemaSupertema(@PathVariable int idTema, @PathVariable int idSupertema) {
        TemaSupertema temaSupertema = service.desvincularTemaSupertema(idTema, idSupertema);
        return new ResponseEntity<TemaSupertema>(temaSupertema, HttpStatus.OK);
    }

    @DeleteMapping(value = "/textos/{idTexto}/temas/{idTema}")
    public ResponseEntity desvincularTextoTema(@PathVariable int idTexto, @PathVariable int idTema) {
        TextoTema textoTema = service.desvincularTextoTema(idTexto, idTema);
        return new ResponseEntity<TextoTema>(textoTema, HttpStatus.OK);
    }
    @DeleteMapping(value = "/textos/{idTexto}/autores/{idAutor}")
    public ResponseEntity desvincularTextoAutor(@PathVariable int idTexto, @PathVariable int idAutor) {
        TextoAutor textoAutor = service.desvincularTextoAutor(idTexto, idAutor);
        return new ResponseEntity<TextoAutor>(textoAutor, HttpStatus.OK);
    }

//PUT resource/{id} + body data -->Actualizar un recurso
    @PutMapping(value = "/autores/{id}")
    public ResponseEntity actualizarAutorPorId(@PathVariable int id, @RequestBody AutorNuevo autor) {
        AutorDto autorNuevo = service.actualizarAutorPorId(id, autor);
        return new ResponseEntity<AutorDto>(autorNuevo, HttpStatus.OK);
    }

    @PutMapping(value = "/textos/{id}")
    public ResponseEntity actualizarTextosPorId(@PathVariable int id, @RequestBody TextoNuevo texto) {
        TextoDto textoNuevo = service.actualizarTextoPorId(id, texto);
        return new ResponseEntity<TextoDto>(textoNuevo, HttpStatus.OK);
    }

    @PutMapping(value = "/temas/{id}")
    public ResponseEntity actualizarTemasPorId(@PathVariable int id, @RequestBody TemaNuevo tema) {
        TemaDto temaNuevo = service.actualizarTemaPorId(id, tema);
        return new ResponseEntity<TemaDto>(temaNuevo, HttpStatus.OK);
    }

    @PutMapping(value = "/supertemas/{id}")
    public ResponseEntity actualizarSupertemasPorId(@PathVariable int id, @RequestBody SupertemaNuevo supertema) {
        SupertemaDto supertemaNuevo = service.actualizarSupertemaPorId(id, supertema);
        return new ResponseEntity<SupertemaDto>(supertemaNuevo, HttpStatus.OK);
    }

//DELETE resource/{id} --> Eliminar recursos por id

    @DeleteMapping("autores/{idAutor}")
    public ResponseEntity eliminarAutorPorId(@PathVariable int idAutor){
        AutorDto autorDto = service.recuperarAutorPorId(idAutor);
        service.eliminarAutorPorId(idAutor);
        return new ResponseEntity<AutorDto>(autorDto,HttpStatus.OK);
    }
    @DeleteMapping("textos/{idTexto}")
    public ResponseEntity eliminarTextoPorId(@PathVariable int idTexto){
        TextoDto textoDto = service.recuperarTextoPorId(idTexto);
        service.eliminarTextoPorId(idTexto);
        return new ResponseEntity<TextoDto>(textoDto,HttpStatus.OK);
    }
    @DeleteMapping("temas/{idTema}")
    public ResponseEntity eliminarTemaPorId(@PathVariable int idTema){
        TemaDto temaDto = service.recuperarTemaPorId(idTema);
        service.eliminarTemaPorId(idTema);
        return new ResponseEntity<TemaDto>(temaDto,HttpStatus.OK);
    }
    @DeleteMapping("supertemas/{idSupertema}")
    public ResponseEntity eliminarSupertemaPorId(@PathVariable int idSupertema){
        SupertemaDto supertemaDto= service.recuperarSupertemaPorId(idSupertema);
        service.eliminarSupertemaPorId(idSupertema);
        return new ResponseEntity<SupertemaDto>(supertemaDto,HttpStatus.OK);
    }

    @DeleteMapping("eliminarTodo")
    public ResponseEntity eliminarTodo(){
        String mensaje = "Todos los recursos han sido eliminados";
        service.eliminarTodo();
        return new ResponseEntity<String>(mensaje,HttpStatus.OK);
    }

}