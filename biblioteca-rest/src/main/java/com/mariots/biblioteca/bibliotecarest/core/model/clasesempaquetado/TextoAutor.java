package com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado;

import com.mariots.biblioteca.bibliotecarest.core.model.dto.AutorDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TextoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextoAutor {
    private TextoDto textoDto;
    private AutorDto autorDto;
}
