package com.jayghz.bookhub.service;

import java.util.List;

import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.CollectionBook;

public interface CollectionBookService {
    CollectionBook addBookToCollection(Integer bookId, Integer collectionId);
    void removeBookFromCollection(Integer bookId, Integer collectionId);
    List<Book> getBooksInCollection(Integer collectionId);
}
