package com.jayghz.bookhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jayghz.bookhub.model.entity.Author;
import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.Category;
import com.jayghz.bookhub.repository.AuthorRepository;
import com.jayghz.bookhub.repository.BookRepository;
import com.jayghz.bookhub.repository.CategoryRepository;
import com.jayghz.bookhub.service.AdminBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminBookServiceImpl implements AdminBookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> paginate(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Transactional
    @Override
    public Book create(Book book) {
        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + book.getCategory().getId()));

        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + book.getAuthor().getId()));

        book.setCategory(category);
        book.setAuthor(author);
        book.setCreatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Integer id, Book updateBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        Category category = categoryRepository.findById(updateBook.getCategory().getId())
                .orElseThrow(
                        () -> new RuntimeException("Category not found with id: " + updateBook.getCategory().getId()));

        Author author = authorRepository.findById(updateBook.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + updateBook.getAuthor().getId()));

        book.setTitle(updateBook.getTitle());
        book.setDescription(updateBook.getDescription());
        book.setPrice(updateBook.getPrice());
        book.setSlug(updateBook.getSlug());
        book.setCoverPath(updateBook.getCoverPath());
        book.setFilePath(updateBook.getFilePath());
        book.setCategory(category);
        book.setAuthor(author);
        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        bookRepository.delete(book);
    }
}
