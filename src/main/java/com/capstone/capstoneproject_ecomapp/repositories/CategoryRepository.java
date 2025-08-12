package com.capstone.capstoneproject_ecomapp.repositories;

import com.capstone.capstoneproject_ecomapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
    Category save(Category category);
}
