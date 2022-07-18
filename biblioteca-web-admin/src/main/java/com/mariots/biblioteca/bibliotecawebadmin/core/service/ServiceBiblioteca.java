package com.mariots.biblioteca.bibliotecawebadmin.core.service;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso.AutorNuevo;
import org.springframework.http.ResponseEntity;

public interface ServiceBiblioteca {
    ResponseEntity<Autor> guardarAutor(AutorNuevo autor);
//    Tema guardarTema(Tema tema);
//    Supertema guardarSupertema(Supertema supertema);
//    Texto guardarTexto(Texto texto);
//    Texto guardarTextoDesdePath(TextoRestSinAT texto, int idAutor, int idTema);
//
//    List<Autor> recuperarAutores();
//    List<Tema> recuperarTemas();
//    List<Supertema> recuperarSupertemas();
//    List<Texto> recupearTextos();
//
//    Autor recuperarAutorPorId(int idAutor);
//    Tema recuperarTemaPorId(int idTema);
//    Supertema recuperarSupertemaPorId(int idSupertema);
//    Texto recuperarTextoPorId(int idTexto);
//
//    Autor recuperarAutorPorNombre(String nombreAutor);
//    Tema recuperarTemaPorNombre(String nombreTema);
//    Supertema recuperarSupertemaPorNombre(String nombreSupertema);
//
//    TemaSupertema vincularTemaSupertema(int idTema, int idSupertema);
//    TemaSupertema sobreescribirVinculoTemaSupertema(int idTema, int idSupertema);
//    TextoTema vincularTextoTema(int idTexto, int idTema);
//    TextoAutor vincularTextoAutor(int idTexto, int idAutor);
//    TextoAutor sobreescribirVinculoTextoAutor(int idTexto, int idAutor);
//
//    TemaSupertema desvincularTemaSupertema(int idTema, int idSupertema);
//    TextoTema desvincularTextoTema(int idTexto, int idTema);
//    TextoAutor desvincularTextoAutor(int idTexto, int idAutor);
//
//    void eliminarAutorPorId(int idAutor);
//    void eliminarTextoPorId(int idTexo);
//    void eliminarTemaPorId(int idTema);
//    void eliminarSupertemaPorId(int idSupertema);
//
//    Autor actualizarAutorPorId(int id, AutorRest autor);
//    Texto actualizarTextoPorId(int id, TextoRest texto);
//    Tema actualizarTemaPorId(int id, TemaRest tema);
//    Supertema actualizarSupertemaPorId(int id, SupertemaRest supertema);



}
