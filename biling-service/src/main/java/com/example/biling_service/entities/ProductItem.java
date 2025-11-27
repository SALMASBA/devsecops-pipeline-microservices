package com.example.biling_service.entities;

import com.example.biling_service.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    @ManyToOne
    private Bill bill;
    private int quantity;
    private double unitPrice;
    @Transient private Product product;
}
