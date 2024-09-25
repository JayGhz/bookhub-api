package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayghz.bookhub.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    
}
