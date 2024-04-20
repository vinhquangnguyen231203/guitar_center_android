package com.example.guitar_center_android.Domain.model;

import java.util.List;

public class OrderBody {
    public static class Order {
        private String address;
        private String phone;

        public Order() {
        }

        public Order(String address, String phone) {
            this.address = address;
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
    public static class OrderDetail {
        private double price;
        private int unit;
        private String productId;

        public OrderDetail() {
        }

        public OrderDetail(double price, int unit, String productId) {
            this.price = price;
            this.unit = unit;
            this.productId = productId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getUnit() {
            return unit;
        }

        public void setUnit(int unit) {
            this.unit = unit;
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
                    "price=" + price +
                    ", unit=" + unit +
                    ", productId='" + productId + '\'' +
                    '}';
        }
    }

    private Order order;
    private List<OrderDetail> orderDetails;

    public OrderBody(Order order, List<OrderDetail> orderDetails) {
        this.order = order;
        this.orderDetails = orderDetails;
    }

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
