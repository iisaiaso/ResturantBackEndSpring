package com.ironman.restaurantmanagement.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

//Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductBodyDto {
    public String name;
    public String description;
    public BigDecimal price;
    public Integer stock;
    public Long categoryId;
}
