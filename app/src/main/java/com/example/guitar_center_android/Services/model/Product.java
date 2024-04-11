package com.example.guitar_center_android.Services.model;

public class Product {
    private int productId;
    private String productName;
    private int unit;
    private Double price;
    private String image;
    private String categoryId;
    private String description;

    //CONSTRUCTOR

    public Product(int productId, String productName, int unit, Double price, String image, String categoryId, String description) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.price = price;
        this.image = image;
        this.categoryId = categoryId;
        this.description = description;
    }

    //METHODS
    //GETTER AND SETTER

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //to String

}
