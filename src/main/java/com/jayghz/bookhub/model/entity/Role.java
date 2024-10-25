package com.jayghz.bookhub.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import com.jayghz.bookhub.model.enums.ERole;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ERole name;
}
