package com.mariots.biblioteca.bibliotecarest.persistence.repository;

import com.mariots.biblioteca.bibliotecarest.persistence.entities.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AutorJpaRepository extends JpaRepository<AutorEntity,Integer> {
    @Query
    Optional<AutorEntity> findByNombreAutor(String nombreAutor);


}
