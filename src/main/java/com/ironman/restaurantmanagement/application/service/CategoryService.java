package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySaveDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;


import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();

    CategoryDto findById(Long id);

    CategorySaveDto create(CategoryBodyDto categoryBodyDto);

    CategorySaveDto update(Long id, CategoryBodyDto categoryBodyDto);

    CategorySaveDto disable(Long id);

    List<CategorySmallDto> findBySate(String state);

    List<CategorySmallDto> findByName(String name);

}
