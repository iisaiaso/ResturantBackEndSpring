package com.ironman.restaurantmanagement.expose.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSaveDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.page.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

// Lombok annotations
@RequiredArgsConstructor

@RestController
@RequestMapping("/products")
@Tag(name = "ProductController", description = "Operations pertaining to products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Mostrar todos los productos")
    @GetMapping
    public List<ProductSmallDto> findAll() {
        return productService.findAll();
    }

    @Operation(summary = "Mostrar un producto por el id")
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return productService.findById(id);
    }

    @Operation(summary = "Mostrar el producto por el estado")
    @GetMapping("/state/{state}")
    public List<ProductSmallDto> findByState(@PathVariable String state) {

        return productService.findByState(state);
    }

    @Operation(summary = "Mostrar el producto por el nombre")
    @GetMapping("/name/{name}")
    public List<ProductSmallDto> findByName(@PathVariable String name) {

        return productService.findByName(name);
    }

    @Operation(summary = "Mostrar el producto por los filtros")
    @GetMapping("/filters")
    public List<ProductSmallDto> findAllFilters(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "state", required = false) String state) {

        return productService.findAllFilters(name, state);
    }

    @GetMapping("/paginated")
    public PageResponse<ProductDto> findAllPaginated(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return productService.findAllPaginated(page, size);
    }

    @Operation(summary = "Agregar un producto")
    @PostMapping
    public ProductSaveDto create(@RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return productService.create(productBody);
    }

    @Operation(summary = "Actualizar un producto por el id")
    @PutMapping("/{id}")
    public ProductSaveDto update(@PathVariable Long id, @RequestBody ProductBodyDto productBody)
            throws DataNotFoundException {
        return productService.update(id, productBody);
    }

    @Operation(summary = "Eliminar una producto por el id")
    @DeleteMapping("/{id}")
    public ProductSaveDto disable(@PathVariable Long id) throws DataNotFoundException {
        return productService.disable(id);
    }
}
