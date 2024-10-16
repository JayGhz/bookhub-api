package com.jayghz.bookhub.dto;

import lombok.Data;

@Data
public class PurchaseItemDTO { // DTO para la visualización de un item de compra
    private Integer id;
    private Float price;
    private Integer quantity;
    private String bookTitle;
}
