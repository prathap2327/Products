package com.scaler.sampleprojectname.dto;

public class CategoryRequestDto {

    private String title;
    private Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryRequestDto{" +
                "title='" + title + '\'' +
                '}';
    }
}
