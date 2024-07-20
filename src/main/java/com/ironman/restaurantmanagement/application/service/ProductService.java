package com.ironman.restaurantmanagement.application.service;

import java.util.List;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSaveDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;

public interface ProductService {
    List<ProductSmallDto> findAll();

    ProductDto findById(Long id) throws DataNotFoundException;

    ProductSaveDto create(ProductBodyDto productBodyDto) throws DataNotFoundException;

    ProductSaveDto update(Long id, ProductBodyDto productBodyDto) throws DataNotFoundException;

    ProductSaveDto disable(Long id) throws DataNotFoundException;

    List<ProductSmallDto> findByState(String state);

    List<ProductSmallDto> findByName(String name);

    List<ProductSmallDto> findAllFilters(String name, String state);

    PageResponse<ProductDto> findAllPaginated(int page, int size);
}
