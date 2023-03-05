package com.coffee.coffee.mapper;

import com.coffee.coffee.model.Product;
import com.coffee.coffee.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto mapToDto(Product product);
    Product mapToEntity(ProductDto productDto);
}
