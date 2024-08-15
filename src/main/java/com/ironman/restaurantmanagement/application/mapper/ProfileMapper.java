package com.ironman.restaurantmanagement.application.mapper;

import com.ironman.restaurantmanagement.application.dto.Profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSaveDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSmallDto;
import com.ironman.restaurantmanagement.persistence.entity.Profile;
import com.ironman.restaurantmanagement.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class})
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);

    ProfileSmallDto toSmallDto(Profile profile);

    ProfileSaveDto toSaveDto(Profile profile);

    Profile entity(ProfileBodyDto profileBodyDto);

    void updateEntity(@MappingTarget Profile profile, ProfileBodyDto profileBodyDto);
}
