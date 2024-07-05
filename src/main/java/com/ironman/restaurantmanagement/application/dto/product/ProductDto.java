package com.ironman.restaurantmanagement.application.dto.product;

import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.persistence.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    public Long id;
    public String name;
    public String description;
    public BigDecimal price;
    public Integer stock;
    public CategorySmallDto category;
    public String state;
    public LocalDateTime createdAt;
    public LocalDateTime updateAt;
}
