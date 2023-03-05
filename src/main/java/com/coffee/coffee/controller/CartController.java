package com.coffee.coffee.controller;

import com.coffee.coffee.model.Cart;
import com.coffee.coffee.model.CartItem;
import com.coffee.coffee.model.User;
import com.coffee.coffee.repository.CartItemRepository;
import com.coffee.coffee.service.CartService;
import com.coffee.coffee.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final UserServiceImpl userService;

    private final CartItemRepository cartItemRepository;
    private final CartService cartService;



    @GetMapping("/cart")
    public String home(Model model,Principal principal){

        if(principal==null){
            return "redirect:/login";
        }
        else if(principal!=null){
        User user = userService.findByUsername(principal.getName());
        for(CartItem c : user.getCart().getCartItems()){
            System.out.println(c.getId());
        }
        model.addAttribute("cartItems",user.getCart().getCartItems());}

        return "cart1";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable ("productId") Long productId,
                            Model model,Principal principal) {

        if(principal==null){
            return "redirect:/login";
        }

        cartService.addToCart(productId,principal);

        return "redirect:/cart";
    }

    @PostMapping("/remove-from-cart/{productId}")
    public void removeFromCart(@PathVariable Long productId, Principal principal, HttpServletResponse response) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();
        CartItem cartItem = cartItemRepository.findByProductId(productId);

//        cart.getCartItems().remove()


        cartItemRepository.delete(cartItem);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
    }
}


