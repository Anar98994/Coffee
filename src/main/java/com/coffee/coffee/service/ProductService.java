package com.coffee.coffee.service;

import com.coffee.coffee.model.Product;
import com.coffee.coffee.model.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getProducts();

    public void saveProduct(Product product);

}
