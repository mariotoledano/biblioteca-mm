package com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso;

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
public class TextoNuevo {

    private String textoString;
    private String longitud;
    private String nombreAutor;
    private List<String> nombreTemas;
}
