package com.ironman.restaurantmanagement.application.service.impl;

import com.ironman.restaurantmanagement.application.dto.Profile.ProfileBodyDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSaveDto;
import com.ironman.restaurantmanagement.application.dto.Profile.ProfileSmallDto;
import com.ironman.restaurantmanagement.application.mapper.ProfileMapper;
import com.ironman.restaurantmanagement.application.service.ProfileService;
import com.ironman.restaurantmanagement.persistence.entity.Profile;
import com.ironman.restaurantmanagement.persistence.repository.ProfileRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Lombok annotations
@RequiredArgsConstructor

// Spring annotations
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public List<ProfileSmallDto> findAll() {
        return profileRepository.findAll()
                                .stream()
                                .map(profileMapper::toSmallDto)
                                .toList();
    }

    @Override
    public ProfileDto findById(Long id) throws Exception {
        return profileRepository.findById(id)
                                .map(profileMapper::toDto)
                                .orElseThrow(profileDataNotFound(id));
    }


    @Override
    public ProfileSaveDto create(ProfileBodyDto profileBody) {

        Profile profile = profileMapper.entity(profileBody);
        profile.setState(State.ENABLED.getValue());
        profile.setCreatedAt(LocalDateTime.now());

        return profileMapper.toSaveDto(profileRepository.save(profile));
    }

    @Override
    public ProfileSaveDto update(Long id, ProfileBodyDto profileBody) throws Exception {
        Profile profile = profileRepository.findById(id)
                                           .orElseThrow(profileDataNotFound(id));

        profileMapper.updateEntity(profile, profileBody);

        return profileMapper.toSaveDto(profileRepository.save(profile));
    }

    @Override
    public ProfileSaveDto disabled(Long id) throws Exception {
        Profile profile = profileRepository.findById(id)
                                           .orElseThrow(profileDataNotFound(id));

        profile.setState(State.DISABLED.getValue());

        return profileMapper.toSaveDto(profileRepository.save(profile));
    }

    private static Supplier<Exception> profileDataNotFound(Long id) {
        return () -> new Exception("Profile not found with id: " + id);
    }
}
