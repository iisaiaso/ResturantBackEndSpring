package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.product.*;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;

import java.util.List;

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

    PageResponse<ProductDto> paginatedSearch(ProductFilterDto filter);
}
