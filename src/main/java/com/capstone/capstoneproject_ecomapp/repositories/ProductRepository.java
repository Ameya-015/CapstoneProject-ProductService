package com.capstone.capstoneproject_ecomapp.repositories;

import com.capstone.capstoneproject_ecomapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
}
