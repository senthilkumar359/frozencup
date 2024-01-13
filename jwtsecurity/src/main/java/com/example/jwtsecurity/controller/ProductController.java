package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.entity.Product;
import com.example.jwtsecurity.exception.ProductNotFoundException;
import com.example.jwtsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/view")
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @GetMapping("/home")
    public List<Product> getHomeProduct(){
        return productService.getHomeProduct();
    }

    @GetMapping("/product/{id}")
    public Product productEditData(@PathVariable("id") int id) throws ProductNotFoundException {
        return productService.productEditData(id);
    }

    @PostMapping("/edit")
    public void productEditSave(@RequestBody Product data) throws ProductNotFoundException {
        productService.productEditSave(data);
    }

    @PostMapping("/save")
    public void productSave(@RequestBody Product data) {
        productService.productSave(data);
    }

    @DeleteMapping("/delete/{id}")
    public void productDelete (@PathVariable("id") int id) {
        productService.productDelete(id);
    }
}