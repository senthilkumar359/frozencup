package com.example.jwtsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(unique = true,nullable = false)
    private String productName;
    @Column(nullable = false)
    private int productPrice;
    @Column(nullable = false)
    private int productQuantity;
    @Column(nullable = false)
    private String productImageUrl;
}
