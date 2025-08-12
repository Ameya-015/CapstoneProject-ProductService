package com.capstone.capstoneproject_ecomapp.dtos;

import com.capstone.capstoneproject_ecomapp.models.Category;
import com.capstone.capstoneproject_ecomapp.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequest {
    private String name;
    private String description;
    private String category;
    private double price;
    private String imageUrl;

    public static Product toProduct(FakeStoreProductRequest fakeStoreProductRequestDto) {
        Product product = new Product();
        product.setName(fakeStoreProductRequestDto.getName());
        product.setDescription(fakeStoreProductRequestDto.getDescription());
        product.setPrice(fakeStoreProductRequestDto.getPrice());
        product.setImageUrl(fakeStoreProductRequestDto.getImageUrl());
        Category category1 = new Category();
        category1.setName(fakeStoreProductRequestDto.getCategory());
        product.setCategory(category1);

        return product;
    }
}
