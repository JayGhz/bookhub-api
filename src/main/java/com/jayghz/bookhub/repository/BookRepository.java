package com.jayghz.bookhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jayghz.bookhub.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitleOrSlug(String title, String slug);

    List<Book> findTop6ByOrderByCreatedAtDesc();
}
