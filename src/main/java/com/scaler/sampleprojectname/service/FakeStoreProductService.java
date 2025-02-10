package com.scaler.sampleprojectname.service;

import com.scaler.sampleprojectname.dto.FakeStoreResponseDTO;
import com.scaler.sampleprojectname.model.Category;
import com.scaler.sampleprojectname.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreProductService {


    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }
    public Product getProductById(Integer id) {

        Product product = new Product();
       ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/"+ id, FakeStoreResponseDTO.class);

       FakeStoreResponseDTO response = fakeStoreResponse.getBody();
       if(response == null)
       {
           throw new RuntimeException("Product not found");
       }

       product = convertFakeStoreResponseToProduct(response);
       return product;
    }


    public List<Product> getAllProducts()
    {
        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreResponse = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreResponseDTO[].class);
        FakeStoreResponseDTO[] responseArray = fakeStoreResponse.getBody();

        if (responseArray == null) {
            throw new RuntimeException("No products found");
        }

        // Convert each response item to Product and return as a List
        return convertFakeStoreResponseToProducts(responseArray);
    }
    private List<Product> convertFakeStoreResponseToProducts(FakeStoreResponseDTO[] responses) {
        return List.of(responses).stream()
                .map(this::convertFakeStoreResponseToProduct)
                .collect(Collectors.toList());
    }

    private Product convertFakeStoreResponseToProduct(FakeStoreResponseDTO response) {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(response.getCategory());

        product.setId(response.getId());
        product.setCategory(category);
        product.setDescription(response.getDescription());
        product.setImageUrl(response.getImage());
        product.setTitle(response.getTitle());

        return product;
    }

}
