package com.jayghz.bookhub.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayghz.bookhub.dto.PurchaseCreateDTO;
import com.jayghz.bookhub.dto.PurchaseDTO;
import com.jayghz.bookhub.dto.PurchaseReportDTO;
import com.jayghz.bookhub.model.entity.Purchase;
import com.jayghz.bookhub.service.PurchaseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }
    
    
    @PostMapping
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseCreateDTO purchaseCreateDTO) {
        PurchaseDTO savePurchase = purchaseService.createPurchase(purchaseCreateDTO);
        return new ResponseEntity<>(savePurchase, HttpStatus.CREATED);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseDTO>> getPurchasesHistory(@PathVariable Integer userId) {
        List<PurchaseDTO> purchaseHistory = purchaseService.getPurchasesHistoryByUserId(userId);
        return ResponseEntity.ok(purchaseHistory);
    }

    @GetMapping("/report")
    public ResponseEntity<List<PurchaseReportDTO>> getPurchasesReport() {
        List<PurchaseReportDTO> reports = purchaseService.getPurchasesReportByDate();
        return ResponseEntity.ok(reports);
    }
}
