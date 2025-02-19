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
    public Product createProduct(String title,String imageUrl, String description,String title1) {
        Product response ;

        FakeStoreResponseDTO requestBody = new FakeStoreResponseDTO();
        requestBody.setTitle(title);
        requestBody.setImage(imageUrl);
        requestBody.setDescription(description);
        requestBody.setCategory(title1);







        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse =restTemplate.postForEntity("https://fakestoreapi.com/products",requestBody, FakeStoreResponseDTO.class);

        System.out.println("Status code: " + fakeStoreResponse.getStatusCode());
        FakeStoreResponseDTO responseBody = fakeStoreResponse.getBody();
        if (responseBody != null) {
            // Print out the entire response to inspect it.
            System.out.println("Response Body: " + responseBody.toString()); // Make sure to override toString() in FakeStoreResponseDTO if needed.

            // Optionally, print individual fields to check specific ones like description.
            System.out.println("Product Title: " + responseBody.getTitle());
            System.out.println("Product Description: " + responseBody.getDescription());
            System.out.println("Product Image: " + responseBody.getImage());
            System.out.println("Product Category: " + responseBody.getCategory());
        } else {
            System.out.println("Response Body is null.");
        }
        response = convertFakeStoreResponseToProduct(fakeStoreResponse.getBody());
        return  response;
    }

}
