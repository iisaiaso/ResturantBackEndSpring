package com.ironman.restaurantmanagement.application.dto.Profile;

import com.ironman.restaurantmanagement.shared.state.enums.State;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    public Long id;
    public String name;
    public String description;
    public State state;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
