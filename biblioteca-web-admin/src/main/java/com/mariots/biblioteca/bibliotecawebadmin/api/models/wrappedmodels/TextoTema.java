package com.mariots.biblioteca.bibliotecawebadmin.api.models.wrappedmodels;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Tema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Texto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextoTema {
    private Texto texto;
    private Tema tema;
}
