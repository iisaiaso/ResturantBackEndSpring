package com.ironman.restaurantmanagement.application.dto.category;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySaveDto {
    private Long id;
    private String state;

}
