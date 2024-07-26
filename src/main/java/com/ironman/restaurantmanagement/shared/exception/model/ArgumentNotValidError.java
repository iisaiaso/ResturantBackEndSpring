package com.ironman.restaurantmanagement.shared.exception.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArgumentNotValidError {
    private String message;
    private Map<String, String> error;
}
