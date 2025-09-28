package com.capstone.capstoneproject_ecomapp.services;

import com.capstone.capstoneproject_ecomapp.exceptions.ProductNotFoundException;
import com.capstone.capstoneproject_ecomapp.models.Category;
import com.capstone.capstoneproject_ecomapp.models.Product;
import com.capstone.capstoneproject_ecomapp.repositories.CategoryRepository;
import com.capstone.capstoneproject_ecomapp.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDbService")
public class ProductDBService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductDBService(ProductRepository productRepository,
                            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id "+id+" not found");
        }
        return optionalProduct.get();
    }

    @Override
    public Product addProduct(String name, double price, String description, String category, String imageUrl) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);

        Category categoryObj = getCategoryFromDB(category);
        categoryObj.setName(category);
        product.setCategory(categoryObj);

        return productRepository.save(product);
    }

    private Category getCategoryFromDB(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(long id, Product productToUpdate) {
        Product product = productRepository.findById(id).get();

        product.setName(productToUpdate.getName());
        product.setPrice(productToUpdate.getPrice());
        product.setDescription(productToUpdate.getDescription());
        product.setImageUrl(productToUpdate.getImageUrl());

        Category categoryObj = getCategoryFromDB(productToUpdate.getCategory().getName());
        categoryObj.setName(categoryObj.getName());
        product.setCategory(categoryObj);

        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProductByTitle(String title, int pageNumber, int pageSize) {
        Page requestedPage =  productRepository.findByNameContainsIgnoreCase(title,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "names")));
        return requestedPage;
    }

   /* public Product updateHelper(long id ,Product product) {
        Product p =
        return null;
    }*/
}
