package com.ironman.restaurantmanagement.application.dto.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
