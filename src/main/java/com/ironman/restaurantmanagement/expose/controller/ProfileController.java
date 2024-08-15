package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.application.dto.Profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSaveDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSmallDto;
import com.ironman.restaurantmanagement.application.service.ProfileService;
import com.ironman.restaurantmanagement.shared.constants.StatusCode;
import com.ironman.restaurantmanagement.shared.exception.model.GeneralError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Lombok annotations
@RequiredArgsConstructor

@RestController
@RequestMapping("/profiles")
@Tag(name = "ProfileController", description = "Operations pertaining to profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "Mostrar todos los profile")
    @ApiResponse(responseCode = StatusCode.OK, description = "List of all products")
    @GetMapping
    public ResponseEntity<List<ProfileSmallDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(profileService.findAll());
    }


    @Operation(summary = "Mostrar el profile por el id")
    @ApiResponse(responseCode = StatusCode.OK, description = "Product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> findById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(profileService.findById(id));
    }

    @Operation(summary = "Agregar  un profile")
    @ApiResponse(responseCode = StatusCode.CREATED, description = "Profile created")
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            description = "Invalid data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ProfileSaveDto> create(@RequestBody ProfileBodyDto profileBody) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(profileService.create(profileBody));
    }

    @Operation(summary = "Actualizar un profile")
    @ApiResponse(responseCode = StatusCode.OK, description = "Profile update")
    @PutMapping("/{id}")
    public ResponseEntity<ProfileSaveDto> update(@PathVariable("id") Long id,
                                                 @RequestBody ProfileBodyDto profileBody) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(profileService.update(id, profileBody));
    }

    @Operation(summary = "Eliminar un profile")
    @ApiResponse(responseCode = StatusCode.OK, description = "Profile disabled")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileSaveDto> disabled(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(profileService.disabled(id));
    }


}
