package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySaveDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.persistence.entity.Category;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

// MapStruct Annotations
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class})
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    CategorySmallDto toSmallDto(Category category);

    CategorySaveDto toSaveDto(Category category);

    Category toEntity(CategoryBodyDto categoryBody);

    Category updateEntity(@MappingTarget Category category, CategoryBodyDto categoryBodyDto);

}
