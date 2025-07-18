package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productDbService")
public class ProductDBService implements ProductService{

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product addProduct(String name, double price, String description, String category, String imageUrl) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
