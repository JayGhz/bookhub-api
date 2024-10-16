package com.jayghz.bookhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayghz.bookhub.model.entity.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
    List<Collection> findByCustomerId(Integer customerId);
}
