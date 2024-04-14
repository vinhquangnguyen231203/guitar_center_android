package com.example.guitar_center_android.Domain.model;

import java.util.Date;

public class Order {
    private String orderId;
    private String address;
    private String orderDate;
    private String phone;
    private String status;
    private double totalPrice;
    private String username;

    // Constructor
    public Order(String orderId, String address, String orderDate, String phone, String status, double totalPrice, String username) {
        this.orderId = orderId;
        this.address = address;
        this.orderDate = orderDate;
        this.phone = phone;
        this.status = status;
        this.totalPrice = totalPrice;
        this.username = username;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", username='" + username + '\'' +
                '}';
    }
}
