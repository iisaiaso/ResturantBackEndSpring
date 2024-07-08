package com.ironman.restaurantmanagement.shared.page;

import lombok.*;
import lombok.experimental.SuperBuilder;

//Patron builder

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public  abstract class PageableRequest {
    private int page;
    private int size;
    private String sortField;
    private String sortOrder;
}