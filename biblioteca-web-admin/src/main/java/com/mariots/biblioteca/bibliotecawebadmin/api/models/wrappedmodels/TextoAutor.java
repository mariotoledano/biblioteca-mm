package com.mariots.biblioteca.bibliotecawebadmin.api.models.wrappedmodels;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Texto;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextoAutor {
    private Texto texto;
    private Autor autor;
}
