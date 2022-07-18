package com.mariots.biblioteca.bibliotecarest.core.model.dto;

import com.mariots.biblioteca.bibliotecarest.api.validation.TextoRestricciones;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TextoRestricciones(nombreCampoLongitud = "longitud", nombreCampoTextoString = "textoString")
public class TextoDto {
    private int idTexto;
    private String textoString;
    private String longitud;
    private Integer idAutor;
    private List<Integer> idTemas;

}
