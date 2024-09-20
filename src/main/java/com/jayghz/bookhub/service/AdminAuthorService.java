package com.jayghz.bookhub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.jayghz.bookhub.model.entity.Author;

public interface AdminAuthorService {
    List<Author> getAll();
    Page<Author> paginate(Pageable pageable);  
    Author create(Author author);
    Author findById(Integer id);
    Author update(Integer id, Author updateAuthor);
    void delete(Integer id);
}
