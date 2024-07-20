package com.ironman.restaurantmanagement.persistence.repository;

import com.ironman.restaurantmanagement.persistence.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long>,
                ListPagingAndSortingRepository<Product, Long> {

        List<Product> findByStateIgnoreCaseOrderByIdDesc(String state);

        @Query(value = "SELECT p FROM Product AS p " +
                        "WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
                        "ORDER BY p.id DESC")
        List<Product> findByName(@Param("name") String name);

        @Query(value = "SELECT p FROM Product AS p " +
                        "WHERE ( :#{#name} IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%', :name, '%')) ) " +
                        "AND ( :#{#state} IS NULL OR UPPER(p.state) = UPPER(:state) )")
        List<Product> findAllFilters(
                        @Param("name") String name,
                        @Param("state") String state);
}
