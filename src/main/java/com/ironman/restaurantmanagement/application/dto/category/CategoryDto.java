package com.ironman.restaurantmanagement.application.dto.category;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//Lombok Annotation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String url_key;
    private String state;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
