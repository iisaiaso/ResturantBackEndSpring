package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.product.*;
import com.ironman.restaurantmanagement.application.mapper.ProductMapper;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.persistence.entity.Product;
import com.ironman.restaurantmanagement.persistence.enums.ProductSortField;
import com.ironman.restaurantmanagement.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.persistence.repository.ProductRepository;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import com.ironman.restaurantmanagement.shared.page.PagingAndSortingBuilder;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.ironman.restaurantmanagement.shared.util.DateHelper.localDateToString;

// Lombok annotations
@RequiredArgsConstructor

// Spring annotations
@Service
public class ProductServiceImpl extends PagingAndSortingBuilder implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductSmallDto> findAll() {
        return productRepository.findAll()
                                .stream()
                                /* .map(product->productMapper.toSmallDto(product)) */
                                .map(productMapper::toSmallDto)
                                .toList();
    }

    @Override
    public ProductDto findById(Long id) throws DataNotFoundException {
        return productRepository.findById(id)
                                .map(productMapper::toDto)
                                .orElseThrow(() -> productDataNotFoundException(id));
    }

    @Override
    public ProductSaveDto create(ProductBodyDto productBody) throws DataNotFoundException {
        // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                          .orElseThrow(() -> categoryDataNotFoundException(productBody));

        Product product = productMapper.toEntity(productBody);
        product.setState(State.ENABLED.getValue());
        product.setCreatedAt(LocalDateTime.now());

        return productMapper.toSaveDto(productRepository.save(product));
    }

    @Override
    public ProductSaveDto update(Long id, ProductBodyDto productBody) throws DataNotFoundException {
        // Check if product
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> productDataNotFoundException(id));

        // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                          .orElseThrow(() -> categoryDataNotFoundException(productBody));

        productMapper.updateEntity(product, productBody);

        return productMapper.toSaveDto(productRepository.save(product));
    }

    @Override
    public ProductSaveDto disable(Long id) throws DataNotFoundException {
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> productDataNotFoundException(id));

        product.setState(State.DISABLED.getValue());

        return productMapper.toSaveDto(productRepository.save(product));
    }

    @Override
    public List<ProductSmallDto> findByState(String state) {

        return productRepository.findByStateIgnoreCaseOrderByIdDesc(state)
                                .stream()
                                .map(productMapper::toSmallDto)
                                .toList();

    }

    @Override
    public List<ProductSmallDto> findByName(String name) {
        return productRepository.findByName(name)
                                .stream()
                                .map(productMapper::toSmallDto)
                                .toList();
    }

    @Override
    public List<ProductSmallDto> findAllFilters(String name, String state) {
        return productRepository.findAllFilters(name, state)
                                .stream()
                                .map(productMapper::toSmallDto)
                                .toList();
    }

    @Override
    public PageResponse<ProductDto> findAllPaginated(int page, int size) {
        // Variable
        Pageable pageable = PageRequest.of(page - 1, size);

        // Process
        Page<Product> productPage = productRepository.findAll(pageable);

        // Result
        return buildPageResponse(productPage, productMapper::toDto);

    }

    @Override
    public PageResponse<ProductDto> paginatedSearch(ProductFilterDto filter) {
        // Variable
        String sortColum = ProductSortField.getSqlColum(filter.getSortField());
        Pageable pageable = buildPageable(filter, sortColum);

        // Process
        var pageProduct = productRepository.paginatedSearch(
                filter.getName(),
                filter.getDescription(),
                // filter.getCategoryId(),
                filter.getState(),
                localDateToString(filter.getCreatedAtFrom()),
                localDateToString(filter.getCreatedAtTo()),
                pageable
        );

        // Result
        return buildPageResponse(pageProduct, productMapper::toDto);
    }

    private static DataNotFoundException productDataNotFoundException(Long id) {
        return new DataNotFoundException("Product not found with id: " + id);
    }

    private static DataNotFoundException categoryDataNotFoundException(ProductBodyDto productBody) {
        return new DataNotFoundException("Category not found with id: " + productBody.getCategoryId());
    }

}
