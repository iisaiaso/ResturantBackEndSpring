package com.ironman.restaurantmanagement.persistence.repository;

import com.ironman.restaurantmanagement.persistence.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {

    List<Category> findByStateIgnoreCaseOrderByIdDesc(String state);
}
