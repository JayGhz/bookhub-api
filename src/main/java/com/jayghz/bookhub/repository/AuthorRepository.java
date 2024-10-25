package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jayghz.bookhub.model.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    
    boolean existsByFirstNameAndLastNameAndUserIdNot(String firstName, String lastName, Integer userId);
}
