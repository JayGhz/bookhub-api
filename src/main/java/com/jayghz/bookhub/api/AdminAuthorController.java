package com.jayghz.bookhub.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayghz.bookhub.model.entity.Author;
import com.jayghz.bookhub.service.AdminAuthorService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("admin/authors")
public class AdminAuthorController {
    private final AdminAuthorService adminAuthorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = adminAuthorService.getAll();
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }
    

    @GetMapping("/paginate")
    public ResponseEntity<Page<Author>> paginateAuthors(
            @PageableDefault(size = 5, sort = "firstName") Pageable pageable) {
        Page<Author> authors = adminAuthorService.paginate(pageable);
        return new ResponseEntity<Page<Author>>(authors, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Integer id) {
        Author author = adminAuthorService.findById(id);
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author newAuthor = adminAuthorService.create(author);
        return new ResponseEntity<Author>(newAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author author) {
        Author updatedAuthor = adminAuthorService.update(id, author);
        return new ResponseEntity<Author>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id) {
        adminAuthorService.delete(id);
        return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }
}
