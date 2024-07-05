package com.ironman.restaurantmanagement.application.dto.product;

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
public class ProductSmallDto {
    public Long id;
    public String name;
    private Long categoryId;
}
