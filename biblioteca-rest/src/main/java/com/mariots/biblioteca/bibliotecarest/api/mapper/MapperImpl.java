package com.mariots.biblioteca.bibliotecarest.api.mapper;

import com.mariots.biblioteca.bibliotecarest.api.exceptions.RecursoNoEncontradoException;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.AutorDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.SupertemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TextoDto;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.AutorNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.SupertemaNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.TemaNuevo;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.TextoNuevo;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.AutorEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.SupertemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TextoEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.repository.RepositoryBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper{

    @Autowired
    RepositoryBiblioteca repository;

    //ENTITY -->> DTO
    @Override
    public AutorDto toDto(AutorEntity autorEntity) {
        AutorDto autorDto = new AutorDto();
        autorDto.setIdAutor(autorEntity.getIdAutor());
        autorDto.setNombreAutor(autorEntity.getNombreAutor());
        autorDto.setFechaAutor(autorEntity.getFechaAutor());
        autorDto.setDescripcionBreve(autorEntity.getDescripcionBreve());
        autorDto.setDescripcionLarga(autorEntity.getDescripcionLarga());
        if (autorEntity.getTextos() == null){
        } else autorDto.setIdTextos(autorEntity.getTextos().stream().map((t)->(t.getIdTexto())).collect(Collectors.toList()));
        return autorDto;
    }

    @Override
    public TextoDto toDto(TextoEntity textoEntity) {
        TextoDto textoDto =new TextoDto();
        textoDto.setIdTexto(textoEntity.getIdTexto());
        textoDto.setTextoString(textoEntity.getTextoString());
        textoDto.setLongitud(textoEntity.getLongitud());
        if (textoEntity.getTemas() == null) {
        } else textoDto.setIdTemas(textoEntity.getTemas().stream().map(TemaEntity::getIdTema).collect(Collectors.toList()));
        if (textoEntity.getAutor() == null) {
        } else textoDto.setIdAutor(textoEntity.getAutor().getIdAutor());
        return textoDto;
    }

    @Override
    public TemaDto toDto(TemaEntity temaEntity) {
        TemaDto temaDto= new TemaDto();
        temaDto.setIdTema(temaEntity.getIdTema());
        temaDto.setNombreTema(temaEntity.getNombreTema());
        if (temaEntity.getSupertema() == null) {
        } else temaDto.setIdSupertema(temaEntity.getSupertema().getIdSupertema());
        if (temaEntity.getTextos() == null) {
        } else temaDto.setIdTextos(temaEntity.getTextos().stream().map(TextoEntity::getIdTexto).collect(Collectors.toList()));
        return temaDto;
    }

    @Override
    public SupertemaDto toDto(SupertemaEntity supertemaEntity) {
        SupertemaDto supertemaDto = new SupertemaDto();
        supertemaDto.setIdSupertema(supertemaEntity.getIdSupertema());
        supertemaDto.setNombreSupertema(supertemaEntity.getNombreSupertema());
        if(supertemaEntity.getTemas() == null){
        } else supertemaDto.setIdTemas(supertemaEntity.getTemas().stream().map(TemaEntity::getIdTema).collect(Collectors.toList()));
        return supertemaDto;
    }

    //DTO -->> ENTITY
    @Override
    public AutorEntity toEntity(AutorDto autorDto) {
        AutorEntity autorEntity = new AutorEntity();
        autorEntity.setNombreAutor(autorDto.getNombreAutor());
        autorEntity.setFechaAutor(autorDto.getFechaAutor());
        autorEntity.setDescripcionBreve(autorDto.getDescripcionBreve());
        autorEntity.setDescripcionLarga(autorDto.getDescripcionLarga());
        if (autorDto.getIdTextos() == null) {
        } else {
            List<TextoEntity> listaTextosEntity= new ArrayList<>();
            autorDto.getIdTextos().stream().map((id)->listaTextosEntity.add(repository.recuperarTextoPorId(id).get())).collect(Collectors.toList());
            autorEntity.setTextos(listaTextosEntity);
        }
        return autorEntity;
    }

    @Override
    public SupertemaEntity toEntity(SupertemaDto supertemaDto) {
        SupertemaEntity supertemaEntity = new SupertemaEntity();
        supertemaEntity.setNombreSupertema(supertemaDto.getNombreSupertema());
        if (supertemaDto.getIdTemas() == null) {
        } else {
            List<TemaEntity> listaTemasEntity = new ArrayList<>();
            supertemaDto.getIdTemas().stream().map((id)->listaTemasEntity.add(repository.recuperarTemaPorId(id).get())).collect(Collectors.toList());
            supertemaEntity.setTemas(listaTemasEntity);
        }
        return supertemaEntity;
    }

    @Override
    public TemaEntity toEntity(TemaDto temaDto) {
        TemaEntity temaEntity= new TemaEntity();
        temaEntity.setNombreTema(temaDto.getNombreTema());
        if (temaEntity.getTextos() == null) {
        }else{
            List<TextoEntity> listaTextosEntity = new ArrayList<>();
            temaDto.getIdTextos().stream().map((id)->listaTextosEntity.add(repository.recuperarTextoPorId(id).get())).collect(Collectors.toList());
            temaEntity.setTextos(listaTextosEntity);
        }
        return temaEntity;
    }

    @Override
    public TextoEntity toEntity(TextoDto textoDto) {
        TextoEntity textoEntity = new TextoEntity();
        textoEntity.setTextoString(textoDto.getTextoString());
        textoEntity.setLongitud(textoDto.getLongitud());
        if (textoDto.getIdAutor() == null) {
        } else{
           textoEntity.setAutor(repository.recuperarAutorPorId(textoDto.getIdAutor()).get());
        }
        if (textoDto.getIdTemas() == null) {
        } else{
            List<TemaEntity> listaTemasEntity = new ArrayList<>();
            textoDto.getIdTemas().stream().map((id)->listaTemasEntity.add(repository.recuperarTemaPorId(id).get())).collect(Collectors.toList());
            textoEntity.setTemas(listaTemasEntity);
        }
        return textoEntity;
    }

    //NUEVO-REST --> DTO
    //VALIDACIONES EN SERVICE
    @Override
    public AutorDto toDto(AutorNuevo autorNuevo) {
        return new AutorDto().builder()
                .nombreAutor(autorNuevo.getNombreAutor())
                .fechaAutor(autorNuevo.getFechaAutor())
                .descripcionBreve(autorNuevo.getDescripcionBreve())
                .descripcionLarga(autorNuevo.getDescripcionLarga())
                .build();
    }

    @Override
    public TextoDto toDto(TextoNuevo textoNuevo) {
        List<Integer> idTemas = new ArrayList<>();
        textoNuevo.getNombreTemas()
                .stream()
                .map((nombreTema)->idTemas.add(
                        repository
                        .recuperarTemaPorNombre(nombreTema)
                        .orElseThrow(()->new RecursoNoEncontradoException("No existe el nombre de tema aportado, ingrese un nombre de tema previamente registrado"))
                        .getIdTema()))
                .collect(Collectors.toList());

        TextoDto textoDto = new TextoDto().builder()
                .textoString(textoNuevo.getTextoString())
                .longitud(textoNuevo.getLongitud())
                .idAutor(repository.recuperarAutorPorNombre(textoNuevo.getNombreAutor())
                        .orElseThrow(()->new RecursoNoEncontradoException("No existe el nombre de autor aportado, ingrese un nombre de autor previamente registrado"))
                        .getIdAutor())
                .idTemas(idTemas)
                .build();
        return textoDto;
    }

    @Override
    public TemaDto toDto(TemaNuevo temaNuevo) {
        return new TemaDto().builder()
                .nombreTema(temaNuevo.getNombreTema())
                .build();
    }

    @Override
    public SupertemaDto toDto(SupertemaNuevo supertemaNuevo) {
        return new SupertemaDto().builder()
                .nombreSupertema(supertemaNuevo.getNombreSupertema())
                .build();
    }

}
