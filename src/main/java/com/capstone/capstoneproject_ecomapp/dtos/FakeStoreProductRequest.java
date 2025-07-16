package com.capstone.capstoneproject_ecomapp.dtos;

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
}
