package com.capstone.capstoneproject_ecomapp.controllers;

import com.capstone.capstoneproject_ecomapp.dtos.ProductResponseDto;
import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Category;
import com.capstone.capstoneproject_ecomapp.models.Product;
import com.capstone.capstoneproject_ecomapp.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    ProductController productController;

    @MockitoBean
    ProductService productService;

    @Test
    public void testGetProductByIdReturnsProductResponseDto(long id) throws ProductNotFoundException {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("TestProduct");
        dummyProduct.setDescription("TestProductDescription");
        dummyProduct.setPrice(199);
        dummyProduct.setImageUrl("demoImg.com");

        Category dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyCategory.setName("TestCategory");
        dummyCategory.setDescription("TestCategoryDescription");

        dummyProduct.setCategory(dummyCategory);

        when(productService.getProductById(1L)).thenReturn(dummyProduct);

//        ProductResponseDto productResponseDto = productController.getProductById(1L);
    }
}
