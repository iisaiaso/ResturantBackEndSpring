package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySaveDto;
import com.ironman.restaurantmanagement.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagement.application.service.CategoryService;
import com.ironman.restaurantmanagement.shared.constants.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotations
@RequiredArgsConstructor

@RestController
@RequestMapping("/categories")
@Tag(name = "CategoryController", description = "Operations pertaining to categories")
public class CategoryController  {

    private final CategoryService categoryService;

    @Operation(summary = "Mostrar todas las categorias")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of all categories")
    @GetMapping
    public ResponseEntity<List<CategorySmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAll());
    }


    @Operation(summary = "Mostrar una categoria por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Category by id")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findById(id));
    }


    @Operation(summary = "Mostrar las categorias por el estado")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by state")
    @GetMapping("/state/{state}")
    public ResponseEntity<List<CategorySmallDto>> findByState(@PathVariable String state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findBySate(state));
    }


    @Operation(summary = "Mostrar las categorias por el nombre")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CategorySmallDto>> findByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findByName(name));
    }


    @Operation(summary = "Mostrar las categorias por filtros")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by filters")
    @GetMapping("/filters")
    public ResponseEntity<List<CategorySmallDto>> findAllFilters(@RequestParam(value = "name", required = false) String name,
                                                                 @RequestParam(value = "state", required = false) String state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAllByFilters(name, state));
    }

    @Operation(summary = "Agregar una categoria")
    @ApiResponse(responseCode = StatusCode.CREATED, description = "Category created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<CategorySaveDto> create(@Valid @RequestBody CategoryBodyDto categoryBodyDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(categoryBodyDto));
    }

    @Operation(summary = "Actualizar una categoria por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Category updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategorySaveDto> update(@PathVariable Long id, @Valid @RequestBody CategoryBodyDto categoryBodyDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.update(id, categoryBodyDto));
    }


    @Operation(summary = "Eliminar una categoria por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Category disabled")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Category not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CategorySaveDto> disable(@PathVariable Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.disable(id));
    }
}
