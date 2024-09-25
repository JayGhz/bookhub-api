package com.jayghz.bookhub.service.impl;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.jayghz.bookhub.model.entity.Purchase;
import com.jayghz.bookhub.model.enums.PaymentStatus;
import com.jayghz.bookhub.repository.PurchaseRepository;
import com.jayghz.bookhub.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    @Transactional
    public Purchase createPurchase(Purchase purchase) {

        

        purchase.setCreatedAt(LocalDateTime.now());

        purchase.setPaymentStatus(PaymentStatus.PENDING);

        // Calcular el total de la compra
        Float total = purchase.getItems().stream()
                .map(item -> item.getBook().getPrice() * item.getQuantity())
                .reduce(0.0f, Float::sum);

        purchase.setTotal(total);
        
        // Relacionar la entidad Purchase con la entidad PurchaseItem
        purchase.getItems().forEach(item -> item.setPurchase(purchase));

        return purchaseRepository.save(purchase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Purchase> getPurchasesHistoryByCustomerId(Integer customerId) {
        return purchaseRepository.findByCustomerId(customerId);
    }

}
