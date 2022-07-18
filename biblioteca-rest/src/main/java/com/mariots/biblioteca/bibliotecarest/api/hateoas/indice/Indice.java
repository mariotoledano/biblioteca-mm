package com.mariots.biblioteca.bibliotecarest.api.hateoas.indice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Indice {

    private MetodosGet metodosGet;
    private MetodosPost metodosPost;
    private MetodosPut metodosPut;
    private MetodosDelete metodosDelete;

}
