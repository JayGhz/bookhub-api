package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jayghz.bookhub.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
