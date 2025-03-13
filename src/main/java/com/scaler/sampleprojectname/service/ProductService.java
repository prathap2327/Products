package com.scaler.sampleprojectname.service;

import com.scaler.sampleprojectname.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(String title,String imageUrl,String catTitle, String description);
    List<Product> getAllProducts();
    Product getProductById(Integer productId);
    Page<Product> getPaginatedProducts(int pageNo, int pageSize);
}
