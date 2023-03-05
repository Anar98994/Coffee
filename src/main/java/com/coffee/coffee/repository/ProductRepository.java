package com.coffee.coffee.repository;

import com.coffee.coffee.model.Product;
import com.coffee.coffee.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Page<Product> findAll(Pageable pageable);


}
