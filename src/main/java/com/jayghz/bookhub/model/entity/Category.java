package com.jayghz.bookhub.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Esta anotación genera automáticamente los métodos getter, setter, equals, hashCode y toString
@Entity // Esta anotación indica que la clase es una entidad de la base de datos
@Table(name = "categories") // Esta anotación indica que la entidad se mapea a la tabla "category" en la base de datos
public class Category {
    @Id // Esta anotación indica que el atributo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esta anotación indica que el valor del atributo se genera automáticamente
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
