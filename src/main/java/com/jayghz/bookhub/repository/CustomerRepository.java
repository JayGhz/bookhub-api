package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayghz.bookhub.model.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    // Método para verificar si ya existe un cliente con el mismo nombre y apellido, excepto el usuario actual
    boolean existsByFirstNameAndLastNameAndUserIdNot(String firstName, String lastName, Integer userId);
}
