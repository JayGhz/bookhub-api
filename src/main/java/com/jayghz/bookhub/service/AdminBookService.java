package com.jayghz.bookhub.service;

import java.util.List;

import org.springframework.data.domain.*;

import com.jayghz.bookhub.model.entity.Book;

public interface AdminBookService {
    List<Book> getAll();
    Page<Book> paginate(Pageable pageable);
    Book create(Book book);
    Book findById(Integer id);
    Book update(Integer id, Book updateBook);
    void delete(Integer id);
}
