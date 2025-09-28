package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductDto;
import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductResponse;
import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_"+id);

        if (product != null) {
            return product;
        }

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
              "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product for id "+id+" does not exist");
        }

        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_"+id, product);

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product addProduct(String name, double price, String description, String category, String imageUrl) {
        FakeStoreProductResponse fakeStoreProductResponse = new FakeStoreProductResponse();
        fakeStoreProductResponse.setTitle(name);
        fakeStoreProductResponse.setPrice(price);
        fakeStoreProductResponse.setDescription(description);
        fakeStoreProductResponse.setCategory(category);
        fakeStoreProductResponse.setImage(imageUrl);

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductResponse, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto fakeStoreProductDto[] = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto fsp : fakeStoreProductDto){
            Product product = fsp.toProduct();
            products.add(product);
        }
        return products;
    }

    @Override
    public Product updateProduct(long id, Product productToUpdate) {
        return null;
    }

    @Override
    public Page<Product> getProductByTitle(String title, int pageNumber, int pageSize) {
        return Page.empty();
    }

//    @Override
//    public void updateProduct(Long id) {
//
//    }

}
