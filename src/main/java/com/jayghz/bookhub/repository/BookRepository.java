package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jayghz.bookhub.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
