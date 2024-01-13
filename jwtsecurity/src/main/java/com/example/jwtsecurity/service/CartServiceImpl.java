package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.Cart;
import com.example.jwtsecurity.entity.Product;
import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.exception.CartNotFoundException;
import com.example.jwtsecurity.exception.CartProductAlreadyExistsException;
import com.example.jwtsecurity.exception.ProductNotFoundException;
import com.example.jwtsecurity.repository.CartRepository;
import com.example.jwtsecurity.repository.ProductRepository;
import com.example.jwtsecurity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CartServiceImpl implements CartService{
    private Logger LOGGER= LoggerFactory.getLogger(getClass());
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Cart saveCartDetails(Product product,int userId) throws CartProductAlreadyExistsException {
        Cart oldCart=new Cart();
        try{
            oldCart.setProductName(" ");
            oldCart=cartRepository.findByProductName(product.getProductName()).get();
        }
        catch(Exception e){}
        if(oldCart.getProductName().equals(product.getProductName())){
            throw new CartProductAlreadyExistsException("Product:"+product.getProductName()+" already exists in the Cart");
        }
        else {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductName(product.getProductName());
            cart.setProductPrice(product.getProductPrice());
            cart.setProductImageUrl(product.getProductImageUrl());
            cart.setProductQuantity(product.getProductQuantity());
            LOGGER.info("Saving the Cart Details");
            return cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> getCartDetails() {
        LOGGER.info("Fetching all the cart objects");
        return cartRepository.findAll();
    }

    @Override
    public Cart updateCartDetails(Cart cart, int cartItemId) throws ProductNotFoundException {
        Cart oldCart=cartRepository.findById(cartItemId).get();
        int quan=oldCart.getQuantity();
        cart.setCartItemId(cartItemId);
        Product product=productRepository.findByProductName(cart.getProductName()).get();
        if(quan<cart.getQuantity()){
            cart.setProductQuantity(cart.getProductQuantity()-(cart.getQuantity()-quan));
            product.setProductQuantity(cart.getProductQuantity());
        }
        else if(quan>cart.getQuantity()){
            cart.setProductQuantity(cart.getProductQuantity()+(quan-cart.getQuantity()));
            product.setProductQuantity(cart.getProductQuantity());
        }
        LOGGER.info("Updating the cart according user selected Quantity");
        productRepository.save(product);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(int id) throws CartNotFoundException {
        try{
            Cart cart=cartRepository.findById(id).get();
            return cart;
        }
        catch (Exception exception){
            throw new CartNotFoundException("The cart with ID:"+id+" is not found");
        }
    }

    @Override
    public Cart findCartByProductName(String productName) throws CartProductAlreadyExistsException {
        Cart cart=new Cart(1,1," ",1,1," ",1);
        cart=cartRepository.findByProductName(productName).get();
        return cart;
    }

    @Override
    public void deleteCartbyId(int cartitemId) throws CartNotFoundException {
        Cart cart=null;
        if((cart=cartRepository.findById(cartitemId).get())!=null){
            Product product=productRepository.findByProductName(cart.getProductName()).get();
            product.setProductQuantity(product.getProductQuantity()+cart.getQuantity());
            productRepository.save(product);
            cartRepository.deleteById(cartitemId);
        }
        else
            throw new CartNotFoundException("Cart with Id:"+cartitemId+" is not found ");
    }

    @Override
    public void addCartToUser(int userId) {
        //User present is to checked
        Set<Cart> cartSet = new HashSet<>();
        List<Cart> carts=cartRepository.findAll();
        System.out.println(carts);
        for(Cart cart:carts){
            cartSet.add(cart);
        }
        User user=userRepository.findById(userId).get();
        user.setCarts(cartSet);
        LOGGER.info("User is added with his selected Cart items");
        userRepository.save(user);
    }

    @Override
    public void deleteAllCart() throws CartNotFoundException {
        LOGGER.info("deleting all Cart details");
        List<Cart> carts=getCartDetails();
        for(Cart cart:carts) {
            deleteCartbyId(cart.getCartItemId());
        }
    }

    @Override
    public void deleteCarts() throws CartNotFoundException {
        LOGGER.info("deleting all Cart details");
        List<Cart> carts=getCartDetails();
        cartRepository.deleteAll(carts);
    }
}
