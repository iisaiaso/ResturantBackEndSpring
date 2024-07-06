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
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
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
    public ProductDto findById(Long id) throws DataNotFoundException{
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> productDataNotFoundException(id));
    }

    @Override
    public ProductSaveDto create(ProductBodyDto productBody) throws DataNotFoundException{
        // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                .orElseThrow(() -> categoryDataNotFoundException(productBody));

        Product product = productMapper.toEntity(productBody);
        product.setState(State.ENABLED.getValue());
        product.setCreatedAt(LocalDateTime.now());

        return productMapper.toSaveDto(productRepository.save(product));
    }

    @Override
    public ProductSaveDto update(Long id, ProductBodyDto productBody) throws DataNotFoundException{
        // Check if product
       Product product = productRepository.findById(id)
                .orElseThrow(()-> productDataNotFoundException(id));

       // Check if category exists
        categoryRepository.findById(productBody.getCategoryId())
                .orElseThrow(() -> categoryDataNotFoundException(productBody));
        productMapper.updateEntity(product,productBody);

        return productMapper.toSaveDto(productRepository.save(product));
    }


    @Override
    public ProductSaveDto disable(Long id) throws DataNotFoundException{
        Product product = productRepository.findById(id)
                .orElseThrow(()-> productDataNotFoundException(id));

        product.setState(State.DISABLED.getValue());

        return productMapper.toSaveDto(productRepository.save(product));
    }

    private static DataNotFoundException productDataNotFoundException(Long id) {
        return new DataNotFoundException("Product not found with id: " + id);
    }

    private static DataNotFoundException categoryDataNotFoundException(ProductBodyDto productBody) {
        return new DataNotFoundException("Category not found with id: " + productBody.getCategoryId());
    }
}