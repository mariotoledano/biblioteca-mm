package com.mariots.biblioteca.bibliotecarest.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="autores")
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idAutor;
    @Column(name="autor", unique = true, nullable = false)
    private String nombreAutor;
    @Column(name="fecha_autor")
    private String fechaAutor;
    @Column(name="descripcion_breve")
    private String descripcionBreve;
    @Column(name="descripcion_larga")
    private String descripcionLarga;
    //JOIN TABLE
    @OneToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "autor")
    private List<TextoEntity> textos;

    }
