package com.jayghz.bookhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Clase DTO par mostrar el reporte de compras con la funcion 
// creada en la base de datos
public class PurchaseReportDTO {
    private Integer quantity;
    private String consultDate;
}
