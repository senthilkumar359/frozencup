package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.Orders;
import com.example.jwtsecurity.exception.NoOrdersFoundException;
import com.example.jwtsecurity.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> getOrderById(int userId) throws NoOrdersFoundException {
        if(orderRepository.findByUserId(userId)!=null) {
            log.info("Orders of user with user Id "+userId+" is being retrieved");
            return orderRepository.findByUserId(userId);
        }
        else{
            throw new NoOrdersFoundException("No orders are found for user with Id: "+userId);
        }
    }

    @Override
    public void saveOrders(List<Orders> ordersList) {
        orderRepository.saveAll(ordersList);
        log.info("All orders of user are saved");
    }

    @Override
    public List<Orders> getAllOrders() {
        log.info("retrieving all users orders");
        return orderRepository.findAll();
    }
}
