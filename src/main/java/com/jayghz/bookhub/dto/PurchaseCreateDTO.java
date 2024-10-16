package com.jayghz.bookhub.dto;

import java.util.List;

import lombok.Data;

@Data
public class PurchaseCreateDTO { // DTO para la creación de una compra
    private Float total;
    private Integer customerId;
    private List<PurchaseItemCreateDTO> items;
}
