package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.dtos.FakeStoreProductDto;
import com.capstone.capstoneproject_ecomapp.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
              "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public void addProduct(Product product) {
        restTemplate.postForObject("https://fakestoreapi.com/products", product, void.class);
    }


}
