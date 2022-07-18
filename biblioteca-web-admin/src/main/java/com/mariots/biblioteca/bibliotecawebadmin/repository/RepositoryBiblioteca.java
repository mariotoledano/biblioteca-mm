package com.mariots.biblioteca.bibliotecawebadmin.repository;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Supertema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Tema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Texto;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso.AutorNuevo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RepositoryBiblioteca {


    public Optional<List<Autor>> recuperarAutores();
    public Optional<List<Tema>> recuperarTemas();
    public Optional<List<Supertema>> recuperarSupertemas();
    public Optional<List<Texto>> recupearTextos();

    public Optional<Autor> recuperarAutorPorId(int idAutor);
    public Optional<Tema> recuperarTemaPorId(int idTema);
    public Optional<Supertema> recuperarSupertemaPorId(int idSupertema);
    public Optional<Texto> recuperarTextoPorId(int idTexto);

    public Optional<Autor> recuperarAutorPorNombre(String nombreAutor);
    public Optional<Tema> recuperarTemaPorNombre(String nombreTema);
    public Optional<Supertema> recuperarSupertemaPorNombre(String nombreSupertema);

    Optional<List<Texto>> recuperarTextosPorAutor(int idAutor);
    Optional<List<Texto>> recuperarTextosPorTema(int idTema);
    Optional<List<Texto>> recuperarTextosPorSupertema(int idSupertema);
    Optional<List<Tema>> recuperarTemasPorSupertema(int idSupertema);


    //Guardar Recurso
    ResponseEntity<Autor> guardarAutor(AutorNuevo autor);
//    public Optional<Tema> guardarTema(TemaNuevo tema);
//    public Optional<Supertema> guardarSupertema(SupertemaNuevo supertema);
//    public Optional<Texto> guardarTexto(TextoNuevo texto, int idAutor, int idTema);

//    TemaSupertema vincularTemaSupertema(int idTema, int idSupertema);
//    TextoTema vincularTextoTema(int idTexto, int idTema);
//    TextoAutor vincularTextoAutor(int idTexto, int idAutor);
//
//    AutorDto actualizarAutorPorId(int id, AutorNuevo autor);
//    TextoDto actualizarTextoPorId(int id, TextoNuevo texto);
//    TemaDto actualizarTemaPorId(int id, TemaNuevo tema);
//    SupertemaDto actualizarSupertemaPorId(int id, SupertemaNuevo supertema);
//
//    TextoAutor sobreescribirVinculoTextoAutor(int idTexto, int idAutor);
//    TemaSupertema sobreescribirVinculoTemaSupertema(int idTema, int idSupertema);
//
//    public void eliminarAutor(Autor autor);
//    public void eliminarTexto(Texto texto);
//    public void eliminarTema(Tema tema);
//    public void eliminarSupertema(Supertema supertema);
//
//    TemaSupertema desvincularTemaSupertema(int idTema, int idSupertema);
//    TextoTema desvincularTextoTema(int idTexto, int idTema);
//    TextoAutor desvincularTextoAutor(int idTexto, int idAutor);




}
