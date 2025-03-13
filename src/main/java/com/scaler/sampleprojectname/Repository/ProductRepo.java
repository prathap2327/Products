package com.scaler.sampleprojectname.Repository;


import com.scaler.sampleprojectname.model.Category;
import com.scaler.sampleprojectname.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Optional<Product> findById(Integer id);

    Optional<Product> findByCategory(Category c);

    Optional<Product> findByIdAndCategory(Integer id, Category c);
}
