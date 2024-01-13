package com.example.jwtsecurity.repository;

import com.example.jwtsecurity.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Orders, Integer> {
    public List<Orders> findByUserId(int userId);


}
