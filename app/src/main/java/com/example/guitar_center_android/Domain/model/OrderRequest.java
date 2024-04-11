package com.example.guitar_center_android.Domain.model;

import com.squareup.moshi.Json;

import java.util.List;

public class OrderRequest {
    @Json(name = "order")
    private Order order;

    @Json(name = "orderDetails")
    private List<OrderDetail> orderDetails;

    // Getters and setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
