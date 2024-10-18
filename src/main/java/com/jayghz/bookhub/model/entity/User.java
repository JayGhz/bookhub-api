package com.jayghz.bookhub.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    // Asociar a la entidad Customer (asocion bidireccional)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private Customer customer;

    // Asociar a la entidad Author (asocion bidireccional)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER) // Cargar el rol de manera inmediata
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
