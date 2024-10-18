package com.jayghz.bookhub.service.impl;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.jayghz.bookhub.dto.PurchaseCreateDTO;
import com.jayghz.bookhub.dto.PurchaseDTO;
import com.jayghz.bookhub.dto.PurchaseReportDTO;
import com.jayghz.bookhub.exception.ResourceNotFoundException;
import com.jayghz.bookhub.mapper.PurchaseMapper;
import com.jayghz.bookhub.model.entity.Book;
import com.jayghz.bookhub.model.entity.Purchase;
import com.jayghz.bookhub.model.entity.User;
import com.jayghz.bookhub.model.enums.PaymentStatus;
import com.jayghz.bookhub.repository.BookRepository;
import com.jayghz.bookhub.repository.PurchaseRepository;
import com.jayghz.bookhub.repository.UserRepository;
import com.jayghz.bookhub.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final PurchaseMapper purchaseMapper;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public PurchaseDTO createPurchase(PurchaseCreateDTO purchaseDTO) {

        // Convertir PurchaseCreateDTO a Purchase
        Purchase purchase = purchaseMapper.toPurchaseEntity(purchaseDTO);

        User user = userRepository.findById(purchaseDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Establecer la fecha de creación de la compra
        purchase.setCreatedAt(LocalDateTime.now());
        // Establecer el estado de pago de la compra
        purchase.setPaymentStatus(PaymentStatus.PENDING);

        purchase.getItems().forEach(item -> {
            Book book = bookRepository.findById(item.getBook().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
            item.setBook(book);
            item.setPurchase(purchase);
        });

        Float total = purchase.getItems().stream()
                .map(item -> item.getQuantity() * item.getPrice())
                .reduce(0.0f, Float::sum);
        purchase.setTotal(total);

        purchase.setUser(user);
        purchase.getItems().forEach(item -> item.setPurchase(purchase));

        Purchase savePurchase = purchaseRepository.save(purchase);

        return purchaseMapper.toPurchaseDTO(savePurchase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseDTO> getPurchasesHistoryByUserId(Integer userId) {
        return purchaseRepository.findByUserId(userId).stream()
                .map(purchaseMapper::toPurchaseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase confirmPurchase(Integer purchaseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmPurchase'");
    }

    @Override
    public Purchase getPurchaseById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPurchaseById'");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReportDTO> getPurchasesReportByDate() {
        List<Object[]> reports = purchaseRepository.getPurchasesReportByDate();
        // Convertir List<Object[]> a List<PurchaseReportDTO>
        List<PurchaseReportDTO> purchaseReportDTOS = reports.stream()
                .map(report -> new PurchaseReportDTO(
                        ((Integer) report[0]).intValue(),
                        (String) report[1]))
                .toList();
        return purchaseReportDTOS;
    }

}
