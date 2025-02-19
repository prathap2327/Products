package com.scaler.sampleprojectname.dto;

public class CreateProductRequestDto {

    private String title;
    private String description;
    private String imageUrl;
    private CategoryRequestDto category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryRequestDto getCategory() {
        return category;
    }

    public void setCategory(CategoryRequestDto category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "CreateProductRequestDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +  // category is an object, so we rely on its own toString()
                '}';
    }
}
