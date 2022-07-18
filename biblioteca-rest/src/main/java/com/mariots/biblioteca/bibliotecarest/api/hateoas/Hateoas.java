package com.mariots.biblioteca.bibliotecarest.api.hateoas;

import com.mariots.biblioteca.bibliotecarest.api.hateoas.indice.*;
import com.mariots.biblioteca.bibliotecarest.core.controller.ControllerBibliotecaRest;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.AutorNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.SupertemaNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.TemaNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.TextoNuevo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Hateoas {

    @Autowired

    public static Indice recuperarIndice(){

        //LINKS RECUPERAR TODOS
        Link linkTodosAutores = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarAutores()).withRel("Recuperar todos los Autores");
        Link linkTodosTextos = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTextos()).withRel("Recuperar todos los Textos");
        Link linkTodosTemas = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTemas()).withRel("Recuperar todos los Temas");
        Link linkTodosSupertemas = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarSupertemas()).withRel("Recuperar todos los Supertemas");
        List<Link> linksTodosRecursos = Arrays.asList(linkTodosAutores,linkTodosTextos, linkTodosTemas, linkTodosSupertemas);


        //LINKS RECUPERAR POR ID
        Link linkRecuperarAutorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarAutorPorId(1)).withRel("Recuperar Autor por Id");
        Link linkRecuperarTextoPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTextoPorId(1)).withRel("Recuperar Texto por Id");
        Link linkRecuperarTemaPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTemaPorId(1)).withRel("Recuperar Tema por Id");
        Link linkRecuperarSupertemaPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarSupertemaPorId(1)).withRel("Recuperar Supertema por Id");
        List<Link> linksRecuperarPorId = Arrays.asList
                (linkRecuperarAutorId,linkRecuperarTextoPorId, linkRecuperarTemaPorId, linkRecuperarSupertemaPorId);

        //LINKS RECUPERAR POR NOMBRE
        Link linkRecuperarAutorNombre = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarAutorPorNombre("nombreAutor")).withRel("Recuperar Autor por Nombre");
        Link linkRecuperarTemaPorNombre = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTemaPorNombre("{nombreTema}")).withRel("Recuperar Tema por Nombre");
        Link linkRecuperarSupertemaPorNombre = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarSupertemaPorNombre("{nombreSupertema}")).withRel("Recuperar Supertema por Nombre");
        List<Link> linksRecuperarPorNombre = Arrays.asList
                (linkRecuperarAutorNombre,linkRecuperarTemaPorNombre, linkRecuperarSupertemaPorNombre);

        //LINKS RECUPERAR POR OTRO RECURSO
        Link linkRecuperarTextosPorAutor = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTextosPorAutor(1)).withRel("Recuperar Textos por Autor");
        Link linkRecuperarTextosPorTema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTextosPorTema(1)).withRel("Recuperar Textos por Tema");
        Link linkRecuperarTextosPorSupertema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTextosPorSupertema(1)).withRel("Recuperar Textos por Supertema");
        Link linkRecuperarTemasPorSupertema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .recuperarTemasPorSupertema(1)).withRel("Recuperar Temas por Supertema");
        List<Link> linksRecuperarPorRecurso = Arrays.asList
                (linkRecuperarTextosPorAutor,linkRecuperarTextosPorTema
                        , linkRecuperarTextosPorSupertema, linkRecuperarTemasPorSupertema);

        //OBJETOS DE MUESTRA PARA  REGISTRO
        AutorNuevo autorNuevo= AutorNuevo
                .builder()
                .nombreAutor("Autor de preba")
                .fechaAutor("Introduzca fecha")
                .descripcionBreve("Descripción breve")
                .descripcionLarga("Descripción larga")
                .build();
        TextoNuevo textoNuevo= TextoNuevo.builder()
                .textoString("texto breve de prueba")
                .longitud("breve")
                .nombreAutor("Epícteto")
                .nombreTemas(Arrays.asList("Coraje","Virtud"))
                .build();
        TemaNuevo temaNuevo = TemaNuevo.builder()
                .nombreTema("Tenacidad")
                .build();
        SupertemaNuevo supertemaNuevo= SupertemaNuevo.builder()
                .nombreSupertema("Naturaleza")
                .build();

        //LINKS REGISTAR NUEVO RECUROS
        Link registrarNuevoAutor = linkTo(methodOn(ControllerBibliotecaRest.class)
                .registrarNuevoAutor(autorNuevo)).withRel("Registrar nuevo Autor");
        Link registrarNuevoTexto = linkTo(methodOn(ControllerBibliotecaRest.class)
                .registrarNuevoTexto(textoNuevo)).withRel("registrar nuevo Texto");
        Link registrarNuevoTema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .registrarNuevoTema(temaNuevo)).withRel("Registrar nuevo Tema");
        Link registrarNuevoSuperetema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .registrarNuevoSupertema(supertemaNuevo)).withRel("Registrar nuevo Supertema");
        List<Link> linksRegistrarNuevo = Arrays.asList
                (registrarNuevoAutor,registrarNuevoTexto, registrarNuevoTema, registrarNuevoSuperetema);

        //LINKS VINCULAR RECURSOS
        Link vincularTemaSupertema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .vincularTemaSupertema(1,1)).withRel("Vincular Tema y Supertema");
        Link vincularTextoTema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .vincularTextoTema(1,1)).withRel("Vincular Texto y Tema");
        Link vincularTextoAutor = linkTo(methodOn(ControllerBibliotecaRest.class)
                .vincularTextoAutor(1,1)).withRel("Vincular Texto y Autor");
        List<Link> linksVincularRecursos = Arrays.asList
                (vincularTemaSupertema,vincularTextoTema, vincularTextoAutor);

        //SOBREESCRIBIR RECURSOS
        Link actualizarAutorPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .actualizarAutorPorId(1,autorNuevo)).withRel("SobreescribirAutor Autor");
        Link actualizarTextosPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .actualizarTextosPorId(1,textoNuevo)).withRel("Sobreescribir Texto");
        Link actualizarTemasPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .actualizarTemasPorId(1, temaNuevo)).withRel("Sobreescribir Tema");
        Link actualizarSupertemasPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .actualizarSupertemasPorId(1,supertemaNuevo)).withRel("Sobreescribir Supertema");
        List<Link> linksSobreescribirRecurso = Arrays.asList
                (actualizarAutorPorId,actualizarTextosPorId, actualizarTemasPorId, actualizarSupertemasPorId);

        //LINKS ACTUALIZAR VINCULOS RECURSOS
        Link sobreescribirVinculoTemaSupertema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .sobreescribirVinculoTemaSupertema(1,1)).withRel("Sobreescribir vínculo Tema Supertema");
        Link sobreescribirVinculoTextoAutor = linkTo(methodOn(ControllerBibliotecaRest.class)
                .sobreescribirVinculoTextoAutor(1,1)).withRel("Sobreescribir vínculo Texto Autor");
        List<Link> linksSobreescribirVinculosRecursos = Arrays.asList
                (sobreescribirVinculoTemaSupertema,sobreescribirVinculoTextoAutor);

        //LINKS ELIMINAR RECURSOS POR ID
        Link eliminarAutorPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .eliminarAutorPorId(1)).withRel("Eliminar Autor por Id");
        Link eliminarTextoPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .eliminarTextoPorId(1)).withRel("Eliminar Texto por Id");
        Link eliminarTemaPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .eliminarTemaPorId(1)).withRel("Eliminar Tema por Id");
        Link eliminarSupertemaPorId = linkTo(methodOn(ControllerBibliotecaRest.class)
                .eliminarSupertemaPorId(1)).withRel("Eliminar Supertema por Id");
        List<Link> linksEliminarRecursoPorId = Arrays.asList
                (eliminarAutorPorId,eliminarTextoPorId, eliminarTemaPorId, eliminarSupertemaPorId);

        //LINKS ELIMINAR VÍNCULOS ENTRE RECURSOS
        Link desvincularTemaSupertema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .desvincularTemaSupertema(1,1)).withRel("Desincular Tema y Supertema");
        Link desvincularTextoTema = linkTo(methodOn(ControllerBibliotecaRest.class)
                .desvincularTextoTema(1,1)).withRel("Desvincular Texto y Tema");
        Link desvincularTextoAutor = linkTo(methodOn(ControllerBibliotecaRest.class)
                .desvincularTextoAutor(1,1)).withRel("Desvincular Texto y Autor");
        List<Link> linksDesvincularRecursos = Arrays.asList
                (desvincularTemaSupertema,desvincularTextoTema, desvincularTextoAutor);


        //CONTSTRUCCIÓN DEL ÍNDICE

        Indice indiceLinks = Indice.builder()
                .metodosGet(MetodosGet.builder()
                        .recuperarTodos(linksTodosRecursos)
                        .recuperarPorId(linksRecuperarPorId)
                        .recuperarPorNombre(linksRecuperarPorNombre)
                        .recuperarPorOtroRecurso(linksRecuperarPorRecurso)
                        .build())
                .metodosPost(MetodosPost.builder()
                        .registrarRecurso(linksRegistrarNuevo)
                        .registrarVinculo(linksVincularRecursos)
                        .build())
                .metodosPut(MetodosPut.builder()
                        .sobreescribirRecurso(linksSobreescribirRecurso)
                        .sobreescribirVinculo(linksSobreescribirVinculosRecursos)
                        .build())
                .metodosDelete(MetodosDelete.builder()
                        .eliminarRecurso(linksEliminarRecursoPorId)
                        .eliminarVinculo(linksDesvincularRecursos)
                        .build())
                .build();

        return indiceLinks;
    }

    public static void añadirLinkIndice(EntityModel<Object> modelo){
        Link linkIndice = linkTo(methodOn(ControllerBibliotecaRest.class).irAlIndice()).withRel("Índice");
        modelo.add(linkIndice);
    }
}
