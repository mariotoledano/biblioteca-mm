package com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado;

import com.mariots.biblioteca.bibliotecarest.core.model.dto.TemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TextoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextoTema {
    private TextoDto textoDto;
    private TemaDto temaDto;
}
