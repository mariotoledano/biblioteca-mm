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
public class TemaDto {

    int idTema;
    String nombreTema;
    private List<Integer> idTextos;
    private Integer IdSupertema;


}
