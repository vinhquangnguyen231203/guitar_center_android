package com.example.guitar_center_android.Repository.Interface;

import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.User;

import java.util.List;

import retrofit2.Response;

public interface ICartRepository {
    boolean insertCart(Product product);
    boolean deleteCart(String id_Product);
    boolean updateCart(Product product);
    List<Product> getAllCart();

}
