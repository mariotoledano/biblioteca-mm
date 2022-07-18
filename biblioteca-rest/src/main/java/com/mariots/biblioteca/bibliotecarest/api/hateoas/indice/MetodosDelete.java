package com.mariots.biblioteca.bibliotecarest.api.hateoas.indice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetodosDelete {
    private List<Link> eliminarVinculo;
    private List<Link> eliminarRecurso;
}
