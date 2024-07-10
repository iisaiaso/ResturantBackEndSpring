package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.product.ProductBodyDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSaveDto;
import com.ironman.restaurantmanagement.application.dto.product.ProductSmallDto;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*@Operation(summary = "Mostrar las categorias por el estado")
    @GetMapping("/state/{state}")
    public List<ProductSmallDto> findByState(@PathVariable String state){
        return categoryService.findBySate(state);
    }*/

    @Operation(summary = "Agregar un producto")
    @PostMapping
    public ProductSaveDto create(@RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return productService.create(productBody);
    }

    @Operation(summary = "Actualizar un producto por el id")
    @PutMapping("/{id}")
    public ProductSaveDto update(@PathVariable Long id, @RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return productService.update(id, productBody);
    }

    @Operation(summary = "Eliminar una producto por el id")
    @DeleteMapping("/{id}")
    public ProductSaveDto disable(@PathVariable Long id) throws DataNotFoundException {
        return productService.disable(id);
    }
}
