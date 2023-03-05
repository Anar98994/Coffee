package com.coffee.coffee.controller;

import com.coffee.coffee.model.CartItem;
import com.coffee.coffee.model.Product;
import com.coffee.coffee.repository.ProductRepository;
import com.coffee.coffee.service.CartServiceImpl;
import com.coffee.coffee.service.ProductService;
import com.coffee.coffee.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class ProductController {
    private final ProductService productServiceImpl;
    private final ProductRepository productRepository;

    @GetMapping
    public String showProduct(@RequestParam(defaultValue = "0") int page,Model model){


        Pageable pageable = PageRequest.of(page, 5); // 10 products per page
        Page<Product> productsPage = productRepository.findAll(pageable);


        model.addAttribute("products",productsPage);
        return "menu";
    }

    @GetMapping("/addProductForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("add-product-form");
        Product product = new Product();
        mav.addObject("product", product);
        return mav;
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Get the filename of the uploaded file
                String filename = file.getOriginalFilename();

                // Save the file to the img folder in your project
                byte[] bytes = file.getBytes();

                System.out.println(filename);
                Path path = Paths.get("src/main/resources/static/img/" + filename);
                Files.write(path, bytes);

                System.out.println(filename);

                // Set the image field in the Product entity to the filename of the uploaded image
                product.setImage(filename);

                // Save the product to the database
                productServiceImpl.saveProduct(product);

                return "redirect:/home";
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/home";
            }
        } else {
            productServiceImpl.saveProduct(product);
            return "redirect:/home";
        }
    }


}
