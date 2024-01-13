package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.Cart;
import com.example.jwtsecurity.entity.Product;
import com.example.jwtsecurity.exception.CartNotFoundException;
import com.example.jwtsecurity.exception.CartProductAlreadyExistsException;
import com.example.jwtsecurity.exception.ProductNotFoundException;

import java.util.List;

public interface CartService {
    Cart saveCartDetails(Product product,int userId) throws CartProductAlreadyExistsException;
    List<Cart> getCartDetails();
    Cart updateCartDetails(Cart cart,int cartItemId) throws ProductNotFoundException;
    Cart getCartById(int id) throws CartNotFoundException;
    Cart findCartByProductName(String productName) throws CartProductAlreadyExistsException;
    void deleteCartbyId(int cartitemId) throws CartNotFoundException;
    void addCartToUser(int userId);
    void deleteAllCart() throws CartNotFoundException;

    void deleteCarts() throws CartNotFoundException;
}
