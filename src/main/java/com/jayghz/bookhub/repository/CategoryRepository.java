package com.jayghz.bookhub.repository;

import com.jayghz.bookhub.model.entity.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz que extiende de JpaRepository para poder realizar operaciones CRUD
public interface CategoryRepository extends JpaRepository<Category, Integer> {
   // Consulta derivada para buscar una categoría por nombre
    Optional<Category> findByName(String name);
}
