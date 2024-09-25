package com.jayghz.bookhub.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayghz.bookhub.dto.AuthorDTO;
import com.jayghz.bookhub.service.AdminAuthorService;

import jakarta.validation.Valid;
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
    public ResponseEntity<List<AuthorDTO>> listAll() {
        List<AuthorDTO> authors = adminAuthorService.getAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<AuthorDTO>> paginate(@PageableDefault(size = 5, sort = "firstName")
                                                     Pageable pageable) {
        Page<AuthorDTO> page = adminAuthorService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = adminAuthorService.create(authorDTO);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Integer id) {
        AuthorDTO author = adminAuthorService.findById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Integer id,@Valid @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = adminAuthorService.update(id, authorDTO);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminAuthorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}