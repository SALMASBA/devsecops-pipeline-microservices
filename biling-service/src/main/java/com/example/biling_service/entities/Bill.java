package com.example.biling_service.entities;

import com.example.biling_service.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter
@Setter @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItem =new ArrayList<>();
    @Transient private Customer customer;
}
