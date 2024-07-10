package com.ironman.restaurantmanagement.shared.page;

import lombok.*;

import java.util.List;

// Lombok annotations
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private Integer number;
    private Integer numberOfElement;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
}
