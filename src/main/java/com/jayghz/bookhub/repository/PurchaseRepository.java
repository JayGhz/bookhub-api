package com.jayghz.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jayghz.bookhub.model.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUserId(Integer userId);

    // Funcion para generar reporte de las compras
    @Query(value = "SELECT * FROM fn_list_purchase_report()", nativeQuery = true) // La funcion es creada en la base de datos
    List<Object[]> getPurchasesReportByDate();
}
