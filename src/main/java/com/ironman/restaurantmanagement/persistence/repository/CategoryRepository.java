package com.ironman.restaurantmanagement.persistence.repository;

import com.ironman.restaurantmanagement.persistence.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {
}
