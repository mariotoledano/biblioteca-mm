package com.mariots.biblioteca.bibliotecarest.core.service;

import com.mariots.biblioteca.bibliotecarest.core.model.dto.AutorDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.SupertemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TextoDto;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.*;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TemaSupertema;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TextoAutor;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TextoTema;

import java.util.List;

public interface ServiceBiblioteca {

    AutorDto guardarAutor(AutorNuevo autorNuevo);
    TemaDto guardarTema(TemaNuevo temaNuevo);
    SupertemaDto guardarSupertema(SupertemaNuevo supertemaNuevo);
    TextoDto guardarTexto(TextoNuevo textoNuevo);
    TextoDto guardarTextoDesdePath(TextoNuevo texto, int idAutor, int idTema);

    List<AutorDto> recuperarAutores();
    List<TemaDto> recuperarTemas();
    List<SupertemaDto> recuperarSupertemas();
    List<TextoDto> recuperarTextos();

    AutorDto recuperarAutorPorId(int idAutor);
    TemaDto recuperarTemaPorId(int idTema);
    SupertemaDto recuperarSupertemaPorId(int idSupertema);
    TextoDto recuperarTextoPorId(int idTexto);

    AutorDto recuperarAutorPorNombre(String nombreAutor);
    TemaDto recuperarTemaPorNombre(String nombreTema);
    SupertemaDto recuperarSupertemaPorNombre(String nombreSupertema);

    List<TextoDto> recuperarTextosPorAutor(int idAutor);
    List<TextoDto> recuperarTextosPorTema(int idTema);
    List<TextoDto> recuperarTextosPorSupertema(int idSupertema);
    List<TemaDto> recuperarTemasPorSupertema(int idSupertema);

    TemaSupertema vincularTemaSupertema(int idTema, int idSupertema);
    TemaSupertema sobreescribirVinculoTemaSupertema(int idTema, int idSupertema);
    TextoTema vincularTextoTema(int idTexto, int idTema);
    TextoAutor vincularTextoAutor(int idTexto, int idAutor);
    TextoAutor sobreescribirVinculoTextoAutor(int idTexto, int idAutor);

    TemaSupertema desvincularTemaSupertema(int idTema, int idSupertema);
    TextoTema desvincularTextoTema(int idTexto, int idTema);
    TextoAutor desvincularTextoAutor(int idTexto, int idAutor);

    void eliminarAutorPorId(int idAutor);
    void eliminarTextoPorId(int idTexo);
    void eliminarTemaPorId(int idTema);
    void eliminarSupertemaPorId(int idSupertema);

    AutorDto actualizarAutorPorId(int id, AutorNuevo autor);
    TextoDto actualizarTextoPorId(int id, TextoNuevo texto);
    TemaDto actualizarTemaPorId(int id, TemaNuevo tema);
    SupertemaDto actualizarSupertemaPorId(int id, SupertemaNuevo supertema);

}
