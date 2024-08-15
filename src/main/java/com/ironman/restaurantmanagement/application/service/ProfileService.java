package com.ironman.restaurantmanagement.application.service;

import com.ironman.restaurantmanagement.application.dto.Profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSaveDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSmallDto;
import java.util.List;

public interface ProfileService {
    List<ProfileSmallDto> findAll();

    ProfileDto findById(Long id) throws Exception;

    ProfileSaveDto create(ProfileBodyDto profileBody);

    ProfileSaveDto update(Long id, ProfileBodyDto profileBody) throws Exception;

    ProfileSaveDto disabled(Long id) throws Exception;
}
