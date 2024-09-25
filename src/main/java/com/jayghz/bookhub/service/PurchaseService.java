package com.jayghz.bookhub.service;

import com.jayghz.bookhub.model.entity.Purchase;
import java.util.List;

public interface PurchaseService {
    Purchase createPurchase(Purchase purchase);
    List<Purchase> getPurchasesHistoryByCustomerId(Integer customerId);
}
