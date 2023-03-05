package com.coffee.coffee.service;

import com.coffee.coffee.model.CartItem;

import java.security.Principal;
import java.util.List;

public interface CartService {

    public List<CartItem> getAll();
    public void addToCart(Long productId, Principal principal);


}
