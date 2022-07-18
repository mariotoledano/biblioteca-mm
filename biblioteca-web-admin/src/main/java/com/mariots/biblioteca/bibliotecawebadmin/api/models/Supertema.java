package com.mariots.biblioteca.bibliotecawebadmin.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supertema {

    private int idSupertema;
    private String nombreSupertema;
    private List<Integer> idTemas = new ArrayList<>();


}
