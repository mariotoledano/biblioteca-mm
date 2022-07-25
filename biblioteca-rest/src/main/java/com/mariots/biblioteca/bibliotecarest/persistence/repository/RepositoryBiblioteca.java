package com.mariots.biblioteca.bibliotecarest.persistence.repository;

import com.mariots.biblioteca.bibliotecarest.persistence.entities.AutorEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.SupertemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TextoEntity;

import java.util.List;
import java.util.Optional;

public interface RepositoryBiblioteca {
    public AutorEntity guardarAutor(AutorEntity autorEntity);
    public TemaEntity guardarTema(TemaEntity temaEntity);
    public SupertemaEntity guardarSupertema(SupertemaEntity supertemaEntity);
    public TextoEntity guardarTexto(TextoEntity textoEntity);

    public List<AutorEntity> recuperarAutores();
    public List<TemaEntity> recuperarTemas();
    public List<SupertemaEntity> recuperarSupertemas();
    public List<TextoEntity> recupearTextos();

    public Optional<AutorEntity> recuperarAutorPorId(int idAutor);
    public Optional<TemaEntity> recuperarTemaPorId(int idTema);
    public Optional<SupertemaEntity> recuperarSupertemaPorId(int idSupertema);
    public Optional<TextoEntity> recuperarTextoPorId(int idTexto);

    public Optional<AutorEntity> recuperarAutorPorNombre(String nombreAutor);
    public Optional<TemaEntity> recuperarTemaPorNombre(String nombreTema);
    public Optional<SupertemaEntity> recuperarSupertemaPorNombre(String nombreSupertema);

    public void eliminarAutor(AutorEntity autor);
    public void eliminarTexto(TextoEntity texto);
    public void eliminarTema(TemaEntity tema);
    public void eliminarSupertema(SupertemaEntity supertema);

    public void eliminarAutores();
    public void eliminarTextos();
    public void eliminarTemas();
    public void eliminarSupertemas();

}
