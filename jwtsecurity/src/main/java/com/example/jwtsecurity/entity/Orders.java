package com.example.jwtsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int quantity;
    private int totalPrice;
    private String status;
    @Column(nullable = false)
    private int price;

}
