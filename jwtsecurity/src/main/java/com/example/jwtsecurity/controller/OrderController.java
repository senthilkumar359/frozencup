package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.entity.Orders;
import com.example.jwtsecurity.exception.NoOrdersFoundException;
import com.example.jwtsecurity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping(value="/orders/{id}",method = RequestMethod.GET)
    public ResponseEntity getUserProducts(@PathVariable int id) throws NoOrdersFoundException {
        List<Orders> ordersList=orderService.getOrderById(id);
        return new ResponseEntity(ordersList, HttpStatus.OK);
    }
    @RequestMapping(value = "/placeorder",method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity placeOrder(@RequestBody Orders[] orders){
        List<Orders>orderList= Arrays.asList(orders);
        orderService.saveOrders(orderList);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/allorders",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllOrders(){
        List<Orders>allOrdersList=orderService.getAllOrders();
        return new ResponseEntity(allOrdersList,HttpStatus.OK);
    }

}
