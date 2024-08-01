package com.ironman.restaurantmanagement.persistence.repository;

import com.ironman.restaurantmanagement.persistence.entity.Profile;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends ListCrudRepository<Profile, Long>, PagingAndSortingRepository<Profile, Long> {

}
