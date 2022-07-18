package com.mariots.biblioteca.bibliotecarest.persistence.repository;

import com.mariots.biblioteca.bibliotecarest.persistence.entities.TemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TemaJpaRepository extends JpaRepository<TemaEntity,Integer> {
    @Query
    Optional<TemaEntity> findByNombreTema(String nombreTema);
}