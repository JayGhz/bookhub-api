package com.jayghz.bookhub.model.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;
import lombok.EqualsAndHashCode;

// Clase para la clave primaria compuesta de la tabla collection_book
@Embeddable
@EqualsAndHashCode // Lombok genera los métodos equals y hashCode
public class CollectionBookPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_collection_books_books")) 
    private Book book;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_collection_books_collections"))
    private Collection collection;
}
