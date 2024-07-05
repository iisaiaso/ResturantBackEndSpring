package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSaveDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.persistence.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductSmallDto> findAll();

    ProductDto findById(Long id) ;

    ProductSaveDto create(ProductBodyDto productBodyDto);

    ProductSaveDto update(Long id, ProductBodyDto productBodyDto);

    ProductSaveDto disable(Long id);
}
