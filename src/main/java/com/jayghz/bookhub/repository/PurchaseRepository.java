package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayghz.bookhub.model.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByCustomerId(Integer customerId);
}
