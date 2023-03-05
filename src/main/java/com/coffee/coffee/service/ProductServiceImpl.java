package com.coffee.coffee.service;

import com.coffee.coffee.model.Product;
import com.coffee.coffee.model.ProductDto;
import com.coffee.coffee.mapper.ProductMapper;
import com.coffee.coffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public List<ProductDto> getProducts() {
        var productEntityList= productRepository.findAll();


        return productEntityList.parallelStream().
                map(ProductMapper.INSTANCE::mapToDto)
                .toList();

    }
    
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}