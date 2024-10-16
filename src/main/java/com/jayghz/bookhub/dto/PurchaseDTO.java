package com.jayghz.bookhub.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jayghz.bookhub.model.enums.PaymentStatus;

import lombok.Data;

@Data
public class PurchaseDTO { // DTO para la visualización de una compra

    private Integer id;
    private Float total;
    private Integer userId;
    private LocalDateTime createdAt;
    private PaymentStatus paymentStatus;
    private String customerName;
    private List<PurchaseItemDTO> items;

}
