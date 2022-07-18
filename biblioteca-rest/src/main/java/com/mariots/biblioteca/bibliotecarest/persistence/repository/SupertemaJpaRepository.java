package com.mariots.biblioteca.bibliotecarest.persistence.repository;

import com.mariots.biblioteca.bibliotecarest.persistence.entities.SupertemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupertemaJpaRepository extends JpaRepository<SupertemaEntity, Integer> {
    @Query
    Optional<SupertemaEntity> findByNombreSupertema(String nombreSupertema);
}
