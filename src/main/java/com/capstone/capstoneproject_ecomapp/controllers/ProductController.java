package com.capstone.capstoneproject_ecomapp.controllers;

import com.capstone.capstoneproject_ecomapp.dtos.ErrorDto;
import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductDto;
import com.capstone.capstoneproject_ecomapp.dtos.ProductResponseDto;
import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Product;
import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductRequest;
import com.capstone.capstoneproject_ecomapp.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("productDbService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) throws ProductNotFoundException {
        //Jackson library is used to convert the object to JSON format

        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);

        ResponseEntity<ProductResponseDto> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> productList = productService.getAllProducts();

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList) {
            ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @PostMapping("/products")
    public ProductResponseDto addProduct(@RequestBody FakeStoreProductRequest fakeStoreProductRequest) {
        Product product = productService.addProduct(fakeStoreProductRequest.getName(), fakeStoreProductRequest.getPrice(), fakeStoreProductRequest.getDescription(),
                fakeStoreProductRequest.getCategory(), fakeStoreProductRequest.getImageUrl());

        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);

        return productResponseDto;
    }

//    @PutMapping("/products/{id}")
//    public ProductResponseDto updateProduct(@PathVariable Long id) {
//
////        Product product = productService.updateProduct(id);
//        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);
//        return productResponseDto;
//    }

}
