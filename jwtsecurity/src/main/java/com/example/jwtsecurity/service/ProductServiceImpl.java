package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.Product;
import com.example.jwtsecurity.exception.ProductNotFoundException;
import com.example.jwtsecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getHomeProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product productEditData(int id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("this product is not available"));
    }

    @Override
    public void productEditSave(Product data) throws ProductNotFoundException {
        Product product=productRepository.findById(data.getProductId()).orElseThrow(()->new ProductNotFoundException("this product is not available"));
        product.setProductName(data.getProductName());
        product.setProductImageUrl(data.getProductImageUrl());
        product.setProductPrice(data.getProductPrice());
        product.setProductQuantity(data.getProductQuantity());
        productRepository.save(product);
    }

    @Override
    public void productSave(Product data) {
        productRepository.save(data);
    }

    @Override
    public void productDelete(int id) {
        productRepository.deleteById(id);

    }
}
