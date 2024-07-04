package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySaveDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.persistence.entity.Category;
import com.ironman.restaurantmanagement.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Spring annotations
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategorySmallDto> findAll() {
        return  categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public CategorySaveDto create(CategoryBodyDto categoryBodyDto) {
        Category category = categoryMapper.toEntity(categoryBodyDto);
        category.setState(State.ENABLED.getValue());
        category.setCreated_at(LocalDateTime.now());

        return categoryMapper.toSaveDto(categoryRepository.save(category));
    }

    @Override
    public CategorySaveDto update(Long id, CategoryBodyDto categoryBodyDto) {
        Category category = categoryRepository.findById(id).get();

        categoryMapper.updateEntity(category, categoryBodyDto);
        category.setUpdated_at(LocalDateTime.now());

        return categoryMapper.toSaveDto(categoryRepository.save(category));
    }

    @Override
    public CategorySaveDto disable(Long id) {
        Category category = categoryRepository.findById(id).get();
        category.setState(State.DISABLED.getValue());

        return categoryMapper.toSaveDto(categoryRepository.save(category));
    }
}
