package com.ironman.restaurantmanagement.application.dto.product;

import com.ironman.restaurantmanagement.shared.page.PageableRequest;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductFilterDto extends PageableRequest {
    private String name;
    private String description;
    //    private Long categoryId;
    private String state;
    private LocalDate createdAtFrom;
    private LocalDate createdAtTo;
}
