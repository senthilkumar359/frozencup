package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.Product;
import com.example.jwtsecurity.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getProduct();
    List<Product> getHomeProduct();
    Product productEditData(int id) throws ProductNotFoundException;
    void productEditSave(Product data) throws ProductNotFoundException;
    void productSave(Product data);
    void productDelete(int id);
}
