package com.jayghz.bookhub.repository;

import com.jayghz.bookhub.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz que extiende de JpaRepository para poder realizar operaciones CRUD
public interface CategoryRepository extends JpaRepository<Category, Integer> {
   
}
