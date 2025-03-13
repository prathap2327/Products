package com.scaler.sampleprojectname.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Entity
public class Category extends BaseModel implements Serializable {

private String title;

@OneToMany(mappedBy = "category",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
private List<Product>products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category() {}
    public Category(String title)
    {
    this.title = title;
    }
    public String getTitle()
    {

        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
