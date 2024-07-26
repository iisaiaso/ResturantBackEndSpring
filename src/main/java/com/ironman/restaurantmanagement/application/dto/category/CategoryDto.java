package com.ironman.restaurantmanagement.application.dto.category;

import com.ironman.restaurantmanagement.shared.state.enums.State;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
