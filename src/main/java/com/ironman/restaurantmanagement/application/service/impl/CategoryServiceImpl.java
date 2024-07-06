package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySaveDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.persistence.entity.Category;
import com.ironman.restaurantmanagement.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
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
    public CategoryDto findById(Long id) throws DataNotFoundException {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(()-> categoryDataNotFoundException(id));
    }

    @Override
    public CategorySaveDto create(CategoryBodyDto categoryBodyDto) {
        Category category = categoryMapper.toEntity(categoryBodyDto);
        category.setState(State.ENABLED.getValue());
        category.setCreatedAt(LocalDateTime.now());

        return categoryMapper.toSaveDto(categoryRepository.save(category));
    }

    @Override
    public CategorySaveDto update(Long id, CategoryBodyDto categoryBodyDto) throws DataNotFoundException{
        Category category = categoryRepository.findById(id)
                        .orElseThrow(()-> categoryDataNotFoundException(id));

        categoryMapper.updateEntity(category, categoryBodyDto);
        category.setUpdatedAt(LocalDateTime.now());

        return categoryMapper.toSaveDto(categoryRepository.save(category));
    }

    @Override
    public CategorySaveDto disable(Long id) throws DataNotFoundException{
        Category category = categoryRepository.findById(id)
                        .orElseThrow(()-> categoryDataNotFoundException(id));
        category.setState(State.DISABLED.getValue());

        return categoryMapper.toSaveDto(categoryRepository.save(category));
    }

    @Override
    public List<CategorySmallDto> findBySate(String state) {
        return categoryRepository.findByStateIgnoreCaseOrderByIdDesc(state)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<CategorySmallDto> findByName(String name) {
        return categoryRepository.findByName(name)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public List<CategorySmallDto> findAllByFilters(String name, String state) {
        return categoryRepository.findAllByFilters(name,state)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    private static DataNotFoundException categoryDataNotFoundException(Long id) {
        return new DataNotFoundException("Category not found with id: " + id);
    }
}
