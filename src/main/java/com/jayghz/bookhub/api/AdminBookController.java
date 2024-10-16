package com.jayghz.bookhub.api;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.dto.BookCreateUpdateDTO;
import com.jayghz.bookhub.dto.BookDetailsDTO;
import com.jayghz.bookhub.service.AdminBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/books")
public class AdminBookController {
private final AdminBookService adminBookService;

    @GetMapping
    public ResponseEntity<List<BookDetailsDTO>> list() {
        List<BookDetailsDTO> books = adminBookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BookDetailsDTO>> paginate(
            @PageableDefault(size = 5, sort = "title") Pageable pageable) {
        Page<BookDetailsDTO> page = adminBookService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDetailsDTO> create(@Valid @RequestBody BookCreateUpdateDTO bookFormDTO) {
        BookDetailsDTO createdBook = adminBookService.create(bookFormDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> get(@PathVariable Integer id) {
        BookDetailsDTO book = adminBookService.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> update(@PathVariable Integer id,
                                                 @Valid @RequestBody BookCreateUpdateDTO bookFormDTO) {
        BookDetailsDTO updatedBook = adminBookService.update(id, bookFormDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminBookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
