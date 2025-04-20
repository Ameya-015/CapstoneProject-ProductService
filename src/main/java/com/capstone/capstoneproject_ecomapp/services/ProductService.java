package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductDto;
import com.capstone.capstoneproject_ecomapp.models.Product;

public interface ProductService {
    Product getProductById(long id);

    void addProduct(Product product);
}
