package com.example.jwtsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartItemId;
    @Column(nullable = false)
    private int userId;
    @Column(unique = true, nullable = false)
    private String productName;
    @Column(nullable = false)
    private int productPrice;
    @Column(nullable = false)
    private int productQuantity;
    @Column(nullable = false)
    private String productImageUrl;
    private int quantity;
}