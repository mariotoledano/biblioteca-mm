package com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Texto nuevo es co ids no con string, se va a utilizar el proxi de guardar texto por path que usa ids
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextoNuevo {

    private String textoString;
    private String longitud;
    private Integer idAutor;
    private List<Integer> idTemas;
}
