package com.example.guitar_center_android.Domain.model;

public class OrderDetail {
    private String price;
    private int unit;
    private String orderId;
    private String productId;

    // Constructor
    public OrderDetail(String price, int unit, String orderId, String productId) {
        this.price = price;
        this.unit = unit;
        this.orderId = orderId;
        this.productId = productId;
    }

    // Getters and Setters
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "price='" + price + '\'' +
                ", unit=" + unit +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
