package com.ironman.restaurantmanagement.persistence.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

// Lombok annotations
@Getter
@AllArgsConstructor
public enum ProductSortField {
    ID("id", "id"),
    NAME("name", "name"),
    DESCRIPTION("description", "description"),
    CATEGORY_ID("categoryId", "categoryId"),
    STATE("state", "state"),
    CREATED_AT("createdAt", "created_at");

    private final String fieldName;
    private final String columName;

    public static String getSqlColum(String value) {
        return Arrays.stream(ProductSortField.values())
                     .filter(sortField -> sortField.getFieldName()
                                                   .equals(value))
                     .findFirst()
                     .map(ProductSortField::getFieldName)
                     .orElseGet(ID::getColumName);
    }

}
