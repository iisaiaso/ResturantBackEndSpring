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
    public class CategorySmallDto {
        private Long id;
        private String name;
    }

