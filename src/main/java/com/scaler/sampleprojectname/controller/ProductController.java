package com.scaler.sampleprojectname.controller;

import com.scaler.sampleprojectname.dto.CreateProductRequestDto;
import com.scaler.sampleprojectname.model.Product;
import com.scaler.sampleprojectname.service.FakeStoreProductService;
import com.scaler.sampleprojectname.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    private ProductService service;

    public ProductController(@Qualifier("selfProductService") ProductService inputService)
    {

        this.service = inputService;
    }
    @GetMapping("/products/{id}")
    @Cacheable(value = "product",key = "#id")
    public Product getProductById(@PathVariable("id")Integer id)
    {
       Product product = service.getProductById(id);
       if(product == null)
       {
           throw new IllegalArgumentException("product not found");
       }
       return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto  request)
    {

        if(request.getDescription() == null || request.getDescription().isEmpty())
        {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        return service.createProduct(request.getTitle(),request.getImageUrl(),request.getDescription(),request.getCategory().getTitle());

    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return service.getAllProducts();
    }
    @PutMapping("/products/{id}")
    public void UpdateProduct(@PathVariable("id")Integer id)
    {

    }
    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable("id")Integer id)
    {

    }
    @GetMapping("/products/{pageNo}/{pageSize}")
    public ResponseEntity<Page<Product>> getPaginatedProducts(@PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize)
    {
        Page<Product> products =  service.getPaginatedProducts(pageNo,pageSize);
        return ResponseEntity.ok(products);
    }
}
