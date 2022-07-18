package com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado;

import com.mariots.biblioteca.bibliotecarest.core.model.dto.SupertemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TemaDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemaSupertema {
    private TemaDto tema;
    private SupertemaDto supertema;
}
