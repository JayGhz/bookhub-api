package com.jayghz.bookhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.Collection;
import com.jayghz.bookhub.model.entity.CollectionBook;
import com.jayghz.bookhub.repository.CollectionBookRepository;
import com.jayghz.bookhub.repository.CollectionRepository;
import com.jayghz.bookhub.service.CollectionBookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CollectionBookServiceImpl implements CollectionBookService {
    
    private final CollectionBookRepository collectionBookRepository;
    private final CollectionRepository collectionRepository;

    @Override
    public CollectionBook addBookToCollection(Integer bookId, Integer collectionId) {
        LocalDateTime addedDate = LocalDateTime.now();
        collectionBookRepository.addBookToCollection(bookId, collectionId, addedDate);
        
        CollectionBook collectionBook = new CollectionBook();
        collectionBook.setBook(bookId);
        collectionBook.setCollection(collectionId);
        collectionBook.setAddedDate(addedDate);

        return collectionBook;
    }

    @Override
    public void removeBookFromCollection(Integer bookId, Integer collectionId) {
        collectionBookRepository.removeBookFromCollection(bookId, collectionId);
    }

    @Override
    public List<Book> getBooksInCollection(Integer collectionId) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RuntimeException("Collection not found with id: " + collectionId));
        return collectionBookRepository.findBooksByCollection(collection);
    }

}
