package com.mariots.biblioteca.bibliotecarest.persistence.repository;

import com.mariots.biblioteca.bibliotecarest.persistence.entities.AutorEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.SupertemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TextoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryBibliotecaImpl implements RepositoryBiblioteca {
    @Autowired
    AutorJpaRepository autorJpaRepository;
    @Autowired
    TemaJpaRepository temaJpaRepository;
    @Autowired
    SupertemaJpaRepository supertemaJpaRepository;
    @Autowired
    TextoJpaRepository textoJpaRepository;

    //METODOS GUARDAR
    @Override
    public AutorEntity guardarAutor(AutorEntity autorEntity) {
        return autorJpaRepository.save(autorEntity);
    }

    @Override
    public TemaEntity guardarTema(TemaEntity temaEntity) {
        return temaJpaRepository.save(temaEntity);
    }

    @Override
    public SupertemaEntity guardarSupertema(SupertemaEntity supertemaEntity) {
        return supertemaJpaRepository.save(supertemaEntity);
    }

    @Override
    public TextoEntity guardarTexto(TextoEntity textoEntity) {
        return textoJpaRepository.save(textoEntity);
    }

    //MÉTODOS RECUPERAR
    @Override
    public List<AutorEntity> recuperarAutores() {
        return autorJpaRepository.findAll();
    }

    @Override
    public List<TemaEntity> recuperarTemas() {
        return temaJpaRepository.findAll();
    }

    @Override
    public List<SupertemaEntity> recuperarSupertemas() {
        return supertemaJpaRepository.findAll();
    }

    @Override
    public List<TextoEntity> recupearTextos() {
        return textoJpaRepository.findAll();
    }

    //MÉTODOS RECUPERAR POR ID
    @Override
    public Optional<AutorEntity> recuperarAutorPorId(int idAutor) {
        return autorJpaRepository.findById(idAutor);
    }

    @Override
    public Optional<TemaEntity> recuperarTemaPorId(int idTema) {
        return temaJpaRepository.findById(idTema);
    }

    @Override
    public Optional<SupertemaEntity> recuperarSupertemaPorId(int idSupertema) {
        return supertemaJpaRepository.findById(idSupertema);
    }

    @Override
    public Optional<TextoEntity> recuperarTextoPorId(int idTexto) {
        return textoJpaRepository.findById(idTexto);
    }

    @Override
    public Optional<AutorEntity> recuperarAutorPorNombre(String nombreAutor) {
        return autorJpaRepository.findByNombreAutor(nombreAutor);
    }

    @Override
    public Optional<TemaEntity> recuperarTemaPorNombre(String nombreTema) {
        return temaJpaRepository.findByNombreTema(nombreTema);
    }

    @Override
    public Optional<SupertemaEntity> recuperarSupertemaPorNombre(String nombreSupertema) {
        return supertemaJpaRepository.findByNombreSupertema(nombreSupertema);
    }

    @Override
    public void eliminarAutor(AutorEntity autor) {
        autorJpaRepository.delete(autor);
    }

    @Override
    public void eliminarTexto(TextoEntity texto) {
        textoJpaRepository.delete(texto);
    }

    @Override
    public void eliminarTema(TemaEntity tema) {
        temaJpaRepository.delete(tema);
    }

    @Override
    public void eliminarSupertema(SupertemaEntity supertema) {
        supertemaJpaRepository.delete(supertema);
    }

    @Override
    public void eliminarAutores() {autorJpaRepository.deleteAll();

    }

    @Override
    public void eliminarTextos() {textoJpaRepository.deleteAll();

    }

    @Override
    public void eliminarTemas() {temaJpaRepository.deleteAll();

    }

    @Override
    public void eliminarSupertemas() { supertemaJpaRepository.deleteAll();

    }


}
