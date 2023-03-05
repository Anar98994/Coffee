package com.coffee.coffee.controller;

import com.coffee.coffee.model.User;
import com.coffee.coffee.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/checkout")
    public String checkout() {
        // Delete all cart items from the database
        cartItemRepository.deleteAll();
        return "redirect:/check-out-success"; // Redirect to success page
    }

    @GetMapping("/check-out-success")
    public String checkoutSuccess(Model model){

        return "checkout-success";
    }

}
