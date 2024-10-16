package com.jayghz.bookhub.dto;

import lombok.Data;

@Data
public class PurchaseItemCreateDTO { // DTO para la creación de un item de compra
    private Integer bookId;
    private Integer quantity;
    private Float price;
}
