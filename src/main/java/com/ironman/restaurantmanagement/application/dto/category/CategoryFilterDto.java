package com.ironman.restaurantmanagement.application.dto.category;

import com.ironman.restaurantmanagement.shared.page.PageableRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CategoryFilterDto extends PageableRequest {
    private String name;
    private String description;
    private String state;
    private LocalDate createdAtFrom;
    private LocalDate createdAtTo;

}
