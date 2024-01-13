package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.entity.Cart;
import com.example.jwtsecurity.entity.Product;
import com.example.jwtsecurity.exception.CartNotFoundException;
import com.example.jwtsecurity.exception.CartProductAlreadyExistsException;
import com.example.jwtsecurity.exception.ProductNotFoundException;
import com.example.jwtsecurity.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class CartController {

    @Autowired
    private CartService frozenService;

    @RequestMapping(value = "/cart/save/{userId}",method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public Cart saveCart(@RequestBody Product product,@PathVariable(value = "userId")int userId) throws CartProductAlreadyExistsException {
        return frozenService.saveCartDetails(product,userId);
    }

    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Cart> getCarts(){
        return frozenService.getCartDetails();
    }

    @RequestMapping(value="/cart/{id}",method=RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public Cart getCart(@PathVariable(value="id")int id) throws CartNotFoundException {
        return frozenService.getCartById(id);
    }

    @RequestMapping(value="/updatecart/{cartItemId}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public Cart updateCart(@PathVariable(value = "cartItemId") int cartItemId,@RequestBody Cart cart) throws ProductNotFoundException {
        return frozenService.updateCartDetails(cart,cartItemId);//The selected quantity being updated
    }

    @RequestMapping(value="/deletecart/{cartId}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public void deleteCart(@PathVariable(value = "cartId")int cartitemId) throws CartNotFoundException {
        frozenService.deleteCartbyId(cartitemId);
//        return "Cart Deleted Successfully";
    }

    @RequestMapping(value = "/addusercart/{userId}",method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public void userCart(@PathVariable(value = "userId")int userId){
        frozenService.addCartToUser(userId);
    }

    @RequestMapping(value="/deleteallcarts",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public void deleteAllCarts() throws CartNotFoundException {
        frozenService.deleteAllCart();
    }

    @RequestMapping(value = "/deletecarts",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public void deleteCarts() throws CartNotFoundException {
        frozenService.deleteCarts();
    }
}
