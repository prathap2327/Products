package com.scaler.sampleprojectname.controller;

import com.scaler.sampleprojectname.model.Product;
import com.scaler.sampleprojectname.service.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    private FakeStoreProductService service;

    public ProductController(FakeStoreProductService inputService)
    {
        this.service = inputService;
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id")Integer id)
    {
       return service.getProductById(id);
    }

    @PostMapping("/products")
    public void createProduct()
    {

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
}
