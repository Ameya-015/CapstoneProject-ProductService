package com.capstone.capstoneproject_ecomapp.controllers;

import com.capstone.capstoneproject_ecomapp.commons.AuthCommons;
import com.capstone.capstoneproject_ecomapp.dtos.*;
import com.capstone.capstoneproject_ecomapp.exceptions.InvalidTokenException;
import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Product;
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

    AuthCommons authCommons;

    public ProductController(@Qualifier("productDbService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id, @RequestHeader("token") String token) throws ProductNotFoundException {
        //Jackson library is used to convert the object to JSON format

        UserDto userDto = authCommons.validateToken(token);

        if(userDto == null) {
            throw new InvalidTokenException("Token is invalid");
        }

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
        Product product = productService.addProduct(
                fakeStoreProductRequest.getName(),
                fakeStoreProductRequest.getPrice(),
                fakeStoreProductRequest.getDescription(),
                fakeStoreProductRequest.getCategory(),
                fakeStoreProductRequest.getImageUrl());

        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(product);

        return productResponseDto;
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable long id,
                                            @RequestBody FakeStoreProductRequest fakeStoreProductRequestDto) {
        Product product = FakeStoreProductRequest.toProduct(fakeStoreProductRequestDto);
        Product updatedProduct = productService.updateProduct(id, product);

        ProductResponseDto productResponseDto = ProductResponseDto.fromProduct(updatedProduct);
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
