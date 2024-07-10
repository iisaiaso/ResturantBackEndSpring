package com.ironman.restaurantmanagement.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
