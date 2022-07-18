package com.mariots.biblioteca.bibliotecarest.api.mapper;

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

public interface Mapper {

    //Entidad <--> Dto

    AutorDto toDto(AutorEntity autorEntity);
    AutorEntity toEntity(AutorDto autorDto);

    SupertemaDto toDto(SupertemaEntity supertemaEntity);
    SupertemaEntity toEntity(SupertemaDto supertemaDto);

    TemaDto toDto(TemaEntity temaEntity);
    TemaEntity toEntity(TemaDto temaDto);

    TextoDto toDto(TextoEntity textoEntity);
    TextoEntity toEntity(TextoDto textoDto);

    //Rest --> Dto

    AutorDto toDto(AutorNuevo autorNuevo);
    TextoDto toDto(TextoNuevo textoNuevo);
    TemaDto toDto(TemaNuevo temaNuevo);
    SupertemaDto toDto(SupertemaNuevo supertemaNuevo);

}
