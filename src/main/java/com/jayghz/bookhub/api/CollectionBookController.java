package com.jayghz.bookhub.api;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.CollectionBook;
import com.jayghz.bookhub.service.CollectionBookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections-books")
public class CollectionBookController {
    private final CollectionBookService collectionBookService;

    @PostMapping("/{collectionId}/add-book")
    // PathVariable es para obtener el valor de la URL
    // RequestParam es para obtener el valor de la petición
    public ResponseEntity<CollectionBook> addBookToCollection(@PathVariable Integer collectionId, @RequestParam Integer bookId) {
        CollectionBook collectionBook = collectionBookService.addBookToCollection(bookId, collectionId);
        return new ResponseEntity<>(collectionBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{collectionId}/remove-book/{bookId}")
    public ResponseEntity<Void> removeBookFromCollection(@PathVariable Integer collectionId, @PathVariable Integer bookId) {
        collectionBookService.removeBookFromCollection(bookId, collectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{collectionId}/books")
    public ResponseEntity<?> getBooksInCollection(@PathVariable Integer collectionId) {
        List<Book> books = collectionBookService.getBooksInCollection(collectionId);
        return ResponseEntity.ok(books);
    }
}
