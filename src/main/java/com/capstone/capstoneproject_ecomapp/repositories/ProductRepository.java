package com.capstone.capstoneproject_ecomapp.repositories;

import com.capstone.capstoneproject_ecomapp.models.Category;
import com.capstone.capstoneproject_ecomapp.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(long id);

    // Declarative query example -- Predefined method use
    List<Product> findByCategory(Category category);
    // HQL example
    List<Product> findByCategory_Name(String categoryName);

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> getProductByCategoryName(@Param("categoryName") String categoryName);

    // Native query example
    @Query(value = CustomQueries.GET_PRODUCTBY_CATEGORY_NAME, nativeQuery = true)
    List<Product> getProductByCategoryNative(@Param("categoryName") String categoryName);

    Page<Product> findByNameContainsIgnoreCase(String title, Pageable pageable);

}
