package com.coffee.coffee.controller;

import com.coffee.coffee.model.CartItem;
import com.coffee.coffee.model.User;
import com.coffee.coffee.dto.UserDto;
import com.coffee.coffee.repository.CartItemRepository;
import com.coffee.coffee.repository.CartRepository;
import com.coffee.coffee.repository.UserRepository;
import com.coffee.coffee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginPageController {

    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    @GetMapping
    public String getLogin(){

        cartItemRepository.deleteAll();

        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration( @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByUsername(user.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<User> userEntities = userService.findAllUsers();
        model.addAttribute("users", userEntities);
        return "users";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/home/";
        }
        return "redirect:/about";
    }

    @GetMapping("/logout")
    public String logout (Principal principal,HttpServletRequest request, HttpServletResponse response) {

        User user = userService.findByUsername(principal.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
return "redirect:login?logout";
    }
}
