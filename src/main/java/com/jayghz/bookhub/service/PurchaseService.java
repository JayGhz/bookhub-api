package com.jayghz.bookhub.service;

import com.jayghz.bookhub.dto.PurchaseCreateDTO;
import com.jayghz.bookhub.dto.PurchaseDTO;
import com.jayghz.bookhub.dto.PurchaseReportDTO;
import com.jayghz.bookhub.model.entity.Purchase;
import java.util.List;

public interface PurchaseService {
    PurchaseDTO createPurchase(PurchaseCreateDTO purchaseCreateDTO);
    List<PurchaseDTO> getPurchasesHistoryByUserId(Integer userId);
    List<PurchaseReportDTO> getPurchasesReportByDate();

    List<Purchase> getAllPurchases();
    Purchase confirmPurchase(Integer purchaseId);
    Purchase getPurchaseById(Integer id);
}
