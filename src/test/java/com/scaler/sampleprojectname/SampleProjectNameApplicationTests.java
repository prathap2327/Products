package com.scaler.sampleprojectname;

import com.scaler.sampleprojectname.Repository.ProductRepo;
import com.scaler.sampleprojectname.model.Product;
import com.scaler.sampleprojectname.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SampleProjectNameApplicationTests {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testProductRepo()
    {

        Optional<Product> p = productRepo.findById(1);
        if (p.isPresent()) {
            System.out.println("Product found: " + p.get());
        } else {
            System.out.println("Product not found");
        }
    }



}
