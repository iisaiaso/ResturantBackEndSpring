package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.persistence.entity.Category;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Mostrar todas las categorias")
    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @Operation(summary = "Mostrar una categoria por el id")
    @GetMapping("{id}")
    public Category findById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }
}
