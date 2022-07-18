package com.mariots.biblioteca.bibliotecawebadmin.repository;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Supertema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Tema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Texto;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso.AutorNuevo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryBibliotecaImpl implements RepositoryBiblioteca {

    @Autowired
    BibliotecaRestProxy proxy;

    @Override
    public Optional<List<Autor>> recuperarAutores() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Tema>> recuperarTemas() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Supertema>> recuperarSupertemas() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Texto>> recupearTextos() {
        return Optional.empty();
    }
//Recuperar por Id
    @Override
    public Optional<Autor> recuperarAutorPorId(int idAutor) {
        return Optional.empty();
    }

    @Override
    public Optional<Tema> recuperarTemaPorId(int idTema) {
        return Optional.empty();
    }

    @Override
    public Optional<Supertema> recuperarSupertemaPorId(int idSupertema) {
        return Optional.empty();
    }

    @Override
    public Optional<Texto> recuperarTextoPorId(int idTexto) {
        return Optional.empty();
    }
//Recuperar por nombre
    @Override
    public Optional<Autor> recuperarAutorPorNombre(String nombreAutor) {
        return Optional.empty();
    }

    @Override
    public Optional<Tema> recuperarTemaPorNombre(String nombreTema) {
        return Optional.empty();
    }

    @Override
    public Optional<Supertema> recuperarSupertemaPorNombre(String nombreSupertema) {
        return Optional.empty();
    }
//Recuperar por otro Recurso
    @Override
    public Optional<List<Texto>> recuperarTextosPorAutor(int idAutor) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Texto>> recuperarTextosPorTema(int idTema) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Texto>> recuperarTextosPorSupertema(int idSupertema) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Tema>> recuperarTemasPorSupertema(int idSupertema) {
        return Optional.empty();
    }

    //Guardar Recurso
    @Override
    public ResponseEntity<Autor> guardarAutor(AutorNuevo autor) {
        return proxy.registrarNuevoAutor(autor);
    }



}
