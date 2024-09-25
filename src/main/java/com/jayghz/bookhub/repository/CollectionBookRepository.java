package com.jayghz.bookhub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.*;

import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.Collection;
import com.jayghz.bookhub.model.entity.CollectionBook;
import com.jayghz.bookhub.model.entity.CollectionBookPK;

public interface CollectionBookRepository extends JpaRepository<CollectionBook, CollectionBookPK> {
    
    // Añade un libro a una colección usando una consulta nativa de SQL
    @Query(value = "INSERT INTO collection_book (book_id, collection_id, date_added) VALUES (:bookId, :collectionId, :dateAdded)", nativeQuery = true)
    void addBookToCollection(@Param("bookId") Integer bookId, @Param("collectionId") Integer collectionId, @Param("dateAdded") LocalDateTime dateAdded);
    

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM collection_book WHERE book_id = :bookId AND collection_id = :collectionId", nativeQuery = true)
    void removeBookFromCollection(@Param("bookId") Integer bookId, @Param("collectionId") Integer collectionId);

    @Query("SELECT cb.book FROM CollectionBook cb WHERE cb.collection = :collection")
    List<Book> findBooksByCollection(Collection collection);
}
