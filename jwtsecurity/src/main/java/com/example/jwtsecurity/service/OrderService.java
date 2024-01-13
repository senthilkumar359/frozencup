package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.Orders;
import com.example.jwtsecurity.exception.NoOrdersFoundException;

import java.util.List;

public interface OrderService {
    List<Orders> getOrderById(int userId) throws NoOrdersFoundException;

    void saveOrders(List<Orders> ordersList);

    List<Orders> getAllOrders();
}

