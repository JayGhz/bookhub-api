package com.jayghz.bookhub.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import java.util.List;

import com.jayghz.bookhub.model.enums.PaymentStatus;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float total;
    
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_purchase_users"))
    private User user;

    // Relacionar la entidad Purchase con la entidad PurchaseItem
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseItem> items;
}   
