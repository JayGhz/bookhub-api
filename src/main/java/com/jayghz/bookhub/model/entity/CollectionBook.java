package com.jayghz.bookhub.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "collection_books")
@IdClass(CollectionBookPK.class)
public class CollectionBook {
    @Id
    private Integer book;

    @Id
    private Integer collection;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedDate;
}

// Nota: Si se desea generar un atabl compuest solo de las llaves foraneas, se podria usar la anotacion @ManyToMany
