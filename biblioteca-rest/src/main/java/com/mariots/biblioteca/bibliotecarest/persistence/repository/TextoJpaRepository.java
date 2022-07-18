package com.mariots.biblioteca.bibliotecarest.persistence.repository;

import com.mariots.biblioteca.bibliotecarest.persistence.entities.TextoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextoJpaRepository extends JpaRepository<TextoEntity, Integer> {
}
