package com.mariots.biblioteca.bibliotecawebadmin.api.models.nuevorecurso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorNuevo {
    private String nombreAutor;
    private String fechaAutor;
    private String descripcionBreve;
    private String descripcionLarga;
}
