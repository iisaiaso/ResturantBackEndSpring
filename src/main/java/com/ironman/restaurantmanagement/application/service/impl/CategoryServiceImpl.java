package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.category.*;
import com.ironman.restaurantmanagement.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.persistence.entity.Category;
import com.ironman.restaurantmanagement.persistence.enums.CategorySortField;
import com.ironman.restaurantmanagement.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ironman.restaurantmanagement.shared.util.DateHelper.localDateToString;


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

    @Override
    public Page<CategoryDto> findAllPaginated(int page, int size) {
        // Variables
        Pageable pageable = PageRequest.of(page,size);

        // Process
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        // Result
        return categoryPage.map(categoryMapper::toDto);
    }

    @Override
    public PageResponse<CategoryDto> paginatedSearch(CategoryFilterDto filter) {
        // Variables
        String colum = CategorySortField.getSqlColumn(filter.getSortField());

        Sort.Direction direction = Sort.Direction
                .fromOptionalString(filter.getSortOrder())
                .orElse(Sort.Direction.DESC);

        Sort sort = Sort.by(direction, colum);

        Pageable pageable =PageRequest.of(
                filter.getPage()-1,
                filter.getSize(),
                sort);

        // Process
        Page<Category> categoryPage = categoryRepository.paginatedSearch(
                filter.getName(),
                filter.getDescription(),
                filter.getState(),
                localDateToString(filter.getCreatedAtFrom()),
                localDateToString(filter.getCreatedAtTo()),
                pageable
        );

        List<CategoryDto> content = categoryPage.getContent()
                .stream()
                .map(categoryMapper::toDto)
                .toList();

        //Result
        return PageResponse.<CategoryDto>builder()
                .content(content)
                .number(categoryPage.getNumber()+1)
                .numberOfElement(categoryPage.getNumberOfElements())
                .size(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .build();
    }

    private static DataNotFoundException categoryDataNotFoundException(Long id) {
        return new DataNotFoundException("Category not found with id: " + id);
    }
}
