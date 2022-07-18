package com.mariots.biblioteca.bibliotecawebadmin.api.models.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextoWeb {

    private String textoString;
    private String longitud;
    private int idAutor;
    private int idTema;

}
