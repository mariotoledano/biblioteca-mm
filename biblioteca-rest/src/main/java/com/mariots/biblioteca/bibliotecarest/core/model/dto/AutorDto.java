package com.mariots.biblioteca.bibliotecarest.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDto {

    private int idAutor;
    private String nombreAutor;
    private String fechaAutor;
    private String descripcionBreve;
    private String descripcionLarga;
    private List<Integer> idTextos;

}
