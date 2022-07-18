package com.mariots.biblioteca.bibliotecawebadmin.api.models.wrappedmodels;

import com.mariots.biblioteca.bibliotecawebadmin.api.models.Supertema;
import com.mariots.biblioteca.bibliotecawebadmin.api.models.Tema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemaSupertema {
    private Tema tema;
    private Supertema supertema;
}
