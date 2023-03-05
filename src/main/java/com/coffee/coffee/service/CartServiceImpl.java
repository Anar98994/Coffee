package com.coffee.coffee.service;

import com.coffee.coffee.model.Cart;
import com.coffee.coffee.model.CartItem;
import com.coffee.coffee.model.Product;
import com.coffee.coffee.model.User;
import com.coffee.coffee.repository.CartItemRepository;
import com.coffee.coffee.repository.CartRepository;
import com.coffee.coffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{


        private final CartRepository cartRepository;
        private final CartItemRepository cartItemRepository;
        private final ProductRepository productRepository;
        private final UserServiceImpl userService;


        @Override
        public List<CartItem> getAll(){
            return cartItemRepository.findAll();
        }

        @Override
        public void addToCart(Long productId, Principal principal){
                User user = userService.findByUsername(principal.getName());
                CartItem cartItem;


                Cart cart = user.getCart() != null ? user.getCart() : new Cart();
                user.setCart(cart);
                cart.setUser(user);
                Product product = productRepository.findById(productId).orElseThrow(()->new IllegalArgumentException("as"));

                if(cartItemRepository.findByProductId(productId)!=null){
                        cartItem = cartItemRepository.findByProductId(productId);
                        cartItem.setQuantity(cartItem.getQuantity()+1);
                }else {
                        cartItem = new CartItem();
                        cartItem.setCart(cart);
                        cartItem.setProduct(product);
                        cartItem.setQuantity(1);
                        cartItem.setTotalPrice(product.getPrice());
                        cartItem.setImage(product.getImage());
                }



                cartRepository.save(cart);
                cartItemRepository.save(cartItem);
                userService.saveUser(user);
        }


}
