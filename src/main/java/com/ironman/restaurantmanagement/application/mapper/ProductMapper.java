package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSaveDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.persistence.entity.Product;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class,
        CategoryMapper.class})
public interface ProductMapper {
    ProductDto toDto(Product product);

    ProductSmallDto toSmallDto(Product product);

    ProductSaveDto toSaveDto(Product product);

    Product toEntity(ProductBodyDto productBodyDto);

    void updateEntity(@MappingTarget Product product, ProductBodyDto productBodyDto);
}