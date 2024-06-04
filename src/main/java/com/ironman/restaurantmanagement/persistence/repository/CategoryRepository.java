package com.ironman.restaurantmanagement.persistence.repository;

import com.ironman.restaurantmanagement.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
