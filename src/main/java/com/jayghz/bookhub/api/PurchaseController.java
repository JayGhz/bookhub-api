package com.jayghz.bookhub.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayghz.bookhub.model.entity.Purchase;
import com.jayghz.bookhub.service.PurchaseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    
    @PostMapping()
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        Purchase savePurchase = purchaseService.createPurchase(purchase);
        return new ResponseEntity<>(savePurchase, HttpStatus.CREATED);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Purchase>> getPurchasesHistoryByCustomerId(@PathVariable Integer customerId) {
        List<Purchase> purchaseHistory = purchaseService.getPurchasesHistoryByCustomerId(customerId);
        return ResponseEntity.ok(purchaseHistory);
    }
}
