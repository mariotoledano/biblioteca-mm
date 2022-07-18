package com.mariots.biblioteca.bibliotecawebadmin.core.service;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso.AutorNuevo;
import com.mariots.biblioteca.bibliotecawebadmin.repository.RepositoryBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceBibliotecaImpl implements ServiceBiblioteca {
    @Autowired
    RepositoryBiblioteca repository;

//GUARDAR
    @Override
    public ResponseEntity<Autor> guardarAutor(AutorNuevo autor) {
        return repository.guardarAutor(autor);
    }



}