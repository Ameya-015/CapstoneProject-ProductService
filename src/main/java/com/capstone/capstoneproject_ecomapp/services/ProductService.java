package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(long id) throws ProductNotFoundException;

    Product addProduct(String name, double price, String description, String category, String imageUrl);

    List<Product> getAllProducts();

    Product updateProduct(long id, Product productToUpdate);
//    void updateProduct(Long id);

    Page<Product> getProductByTitle(String title, int pageNumber, int pageSize);
}
