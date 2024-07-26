package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.*;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();

    CategoryDto findById(Long id) throws DataNotFoundException;

    CategorySaveDto create(CategoryBodyDto categoryBodyDto);

    CategorySaveDto update(Long id, CategoryBodyDto categoryBodyDto) throws DataNotFoundException;

    CategorySaveDto disable(Long id) throws DataNotFoundException;

    List<CategorySmallDto> findBySate(String state);

    List<CategorySmallDto> findByName(String name);

    List<CategorySmallDto> findAllByFilters(String name, String state);

    PageResponse<CategoryDto> findAllPaginated(int page, int size);

    PageResponse<CategoryDto> paginatedSearch(CategoryFilterDto filter);

}
