package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSaveDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.application.mapper.ProductMapper;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.persistence.entity.Product;
import com.ironman.restaurantmanagement.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.persistence.repository.ProductRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

// Spring annotations
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductSmallDto> findAll() {
        return productRepository.findAll()
                .stream()
                /* .map(product->productMapper.toSmallDto(product))*/
                .map(productMapper::toSmallDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ExpressionException("Error: Product with ID " + id + " not found"));
    }

    @Override
    public ProductSaveDto create(ProductBodyDto productBody) {
        // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                .orElseThrow(() -> new ExpressionException("Category not found with id: " + productBody.getCategoryId()));

        Product product = productMapper.toEntity(productBody);
        product.setState(State.ENABLED.getValue());
        product.setCreatedAt(LocalDateTime.now());

        return productMapper.toSaveDto(productRepository.save(product));
    }

    @Override
    public ProductSaveDto update(Long id, ProductBodyDto productBody) {
        // Check if product
       Product product = productRepository.findById(id)
                .orElseThrow(()->new ExpressionException("Product not found with id: "+id));

       // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                .orElseThrow(() -> new ExpressionException("Category not found with id: " + productBody.getCategoryId()));
        productMapper.updateEntity(product,productBody);

        return productMapper.toSaveDto(productRepository.save(product));
    }

    @Override
    public ProductSaveDto disable(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ExpressionException("Product not found with id: "+id));

        product.setState(State.DISABLED.getValue());

        return productMapper.toSaveDto(productRepository.save(product));
    }
}
