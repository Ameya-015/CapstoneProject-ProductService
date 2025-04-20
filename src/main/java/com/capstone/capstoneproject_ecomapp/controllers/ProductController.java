package com.capstone.capstoneproject_ecomapp.controllers;

import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductDto;
import com.capstone.capstoneproject_ecomapp.dtos.ProductResponseDto;
import com.capstone.capstoneproject_ecomapp.models.Category;
import com.capstone.capstoneproject_ecomapp.models.Product;
import com.capstone.capstoneproject_ecomapp.services.FakeStoreProductService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    FakeStoreProductService fakeStoreProductService;

    public ProductController(FakeStoreProductService fakeStoreProductService) {
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) {
        //Jackson library is used to convert the object to JSON format

        Product product = fakeStoreProductService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);

        ResponseEntity<ProductResponseDto> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/products")
    public void addProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = fakeStoreProductDto.toProduct();
        fakeStoreProductService.addProduct(product);
    }
}
