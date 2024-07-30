package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.product.*;
import com.ironman.restaurantmanagement.application.service.ProductService;
import com.ironman.restaurantmanagement.shared.constants.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import com.ironman.restaurantmanagement.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Lombok annotations
@RequiredArgsConstructor

@RestController
@RequestMapping("/products")
@Tag(name = "ProductController", description = "Operations pertaining to products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Mostrar todos los productos")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of all products")
    @GetMapping
    public ResponseEntity<List<ProductSmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findAll());
    }


    @Operation(summary = "Mostrar un producto por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findById(id));
    }


    @Operation(summary = "Mostrar el producto por el estado")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of products by state")
    @GetMapping("/state/{state}")
    public ResponseEntity<List<ProductSmallDto>> findByState(@PathVariable String state) {

        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findByState(state));
    }


    @Operation(summary = "Mostrar el producto por el nombre")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of product by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductSmallDto>> findByName(@PathVariable String name) {

        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findByName(name));
    }


    @Operation(summary = "Mostrar el producto por los filtros")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of products by filters")
    @GetMapping("/filters")
    public ResponseEntity<List<ProductSmallDto>> findAllFilters(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "state", required = false) String state) {

        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findAllFilters(name, state));
    }


    @Operation(summary = "Paginado y ordenamiento de los productos")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of products paginated")
    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<ProductDto>> findAllPaginated(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.findAllPaginated(page, size));
    }


    @Operation(summary = "Busqueda paginada de los productos")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of products with pagination")
    @GetMapping("/paginatedSearch")
    public ResponseEntity<PageResponse<ProductDto>> paginatedSearch(
            @NotNull(message = "Page number is required")
            @Min(value = 1, message = "Page number must be positive")
            @RequestParam(value = "page", defaultValue = "1") int page,

            @NotNull(message = "Size number is required")
            @Min(value = 1, message = "Size number must be positive")
            @RequestParam(value = "size", defaultValue = "10") int size,

            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,

            @Parameter(description = "Category id must be a positive a number")
            @PositiveOrZero(message = "Category id must be a positive a number")
            @RequestParam(value = "categoryId", required = false) Long categoryId,

            @Parameter(description = "Stock must be a positive number")
            @PositiveOrZero(message = "Stock must be a positive number")
            @RequestParam(value = "stock", required = false) Integer stock,

            @Parameter(description = "State must be 'A' for enabled or 'E' disabled")
            @Pattern(regexp = "^[AE]$", message = "State must be 'A' for enabled or 'E' disabled")
            @RequestParam(value = "state", required = false) String state,

            @RequestParam(value = "createdAtFrom", required = false) LocalDate createdAtFrom,
            @RequestParam(value = "createdAtTo", required = false) LocalDate createdAtTo,

            @Parameter(description = "Sort field must be 'id', 'name', 'description', 'categoryId', 'stock', 'state' or 'createdAt' (default:'id')")
            @Pattern(regexp = "^(id|name|description|categoryId|stock|state|createdAt)$", message = "Sort field must be 'id', 'name', 'description', 'categoryId', 'stock', 'state' or 'createdAt' (default:'id')")
            @RequestParam(value = "sortField", required = false) String sortField,

            @Parameter(description = "Sort order must be 'ASC' or 'DESC' (default:'DESC')")
            @Pattern(regexp = "^(ASC|DESC)$", message = "Sort order must be 'ASC' or 'DESC' (default:'DESC')")
            @RequestParam(value = "sortOrder", required = false) String sortOrder
    ) {
        var filter = ProductFilterDto.builder()
                                     .page(page)
                                     .size(size)
                                     .name(name)
                                     .description(description)
                                     .categoryId(categoryId)
                                     .stock(stock)
                                     .state(state)
                                     .createdAtFrom(createdAtFrom)
                                     .createdAtTo(createdAtTo)
                                     .sortField(sortField)
                                     .sortOrder(sortOrder)
                                     .build();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.paginatedSearch(filter));
    }


    @Operation(summary = "Agregar un producto")
    @ApiResponse(responseCode = StatusCode.CREATED, description = "Product created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ProductSaveDto> create(@RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(productService.create(productBody));
    }


    @Operation(summary = "Actualizar un producto por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Product updated")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Product not found",
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
    public ResponseEntity<ProductSaveDto> update(
            @PathVariable Long id,
            @RequestBody ProductBodyDto productBody) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.update(id, productBody));
    }


    @Operation(summary = "Eliminar una producto por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Product disabled")
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            description = "Product not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSaveDto> disable(@PathVariable Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(productService.disable(id));
    }
}
