package com.example.guitar_center_android.Domain.Services.Interface;

import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

public interface ICartServices {
    boolean insertCart(Product product);
    boolean deleteCart(String id_Product);
    boolean updateCart(Product product);
    List<Product> getAllCart();
    boolean deleteAllCart();
    Product getProductById(String productId);
}
