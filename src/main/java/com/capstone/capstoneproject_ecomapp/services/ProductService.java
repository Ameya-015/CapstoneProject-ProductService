package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductDto;
import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getProductById(long id) throws ProductNotFoundException;

    Product addProduct(String name, double price, String description, String category, String imageUrl);

    List<Product> getAllProducts();

    Product updateProduct(long id, Product productToUpdate);
//    void updateProduct(Long id);
}
