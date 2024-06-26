package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySaveDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @Operation(summary = "Mostrar todas las categorias")
    @GetMapping
    public List<CategorySmallDto> findAll() {
        return categoryService.findAll();
    }

    @Operation(summary = "Mostrar una categoria por el id")
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @Operation(summary = "Agregar una categoria")
    @PostMapping
    public CategorySaveDto create(@RequestBody CategoryBodyDto categoryBodyDto) {
        return categoryService.create(categoryBodyDto);
    }

    @Operation(summary = "Actualizar una categoria por el id")
    @PutMapping("/{id}")
    public CategorySaveDto update(@PathVariable Long id, @RequestBody CategoryBodyDto categoryBodyDto) {
        return categoryService.update(id, categoryBodyDto);
    }

    @Operation(summary = "Eliminar una categoria por el id")
    @DeleteMapping("/{id}")
    public CategorySaveDto disable(@PathVariable Long id) {
        return categoryService.disable(id);
    }
}
