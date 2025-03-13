package com.scaler.sampleprojectname.service;

import com.scaler.sampleprojectname.Repository.CategoryRepo;
import com.scaler.sampleprojectname.Repository.ProductRepo;
import com.scaler.sampleprojectname.model.Category;
import com.scaler.sampleprojectname.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo,CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product createProduct(String title, String imageUrl, String description,String catTitle) {
        validateInputRequest(title,imageUrl,description,catTitle);

        Product product = new Product();
        Category category = new Category();
        product.setTitle(title);
        product.setImageUrl(imageUrl);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setDescription(description);
        Optional<Category> existingCategoryOpt = categoryRepo.findByTitle(catTitle);

        if (existingCategoryOpt.isPresent()) {
            // If the category is found, use it
            Category existingCategory = existingCategoryOpt.get();
            product.setCategory(existingCategory);
        } else {
            // If not found, create a new category
            category.setTitle(catTitle);
            product.setCategory(category);
            categoryRepo.save(category);
        }
        // saved category also.

        Product response = productRepo.save(product);
        return response;

    }

    private void validateInputRequest(String title, String imageUrl, String catTitle, String description)
    {
        if(title == null || title.isEmpty())
        {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        Product response = productRepo.findById(id).get();
        System.out.println("Fetched Product: "+response);
        return response;
    }

    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepo.findAll(pageable);
        return productPage;
    }
}
