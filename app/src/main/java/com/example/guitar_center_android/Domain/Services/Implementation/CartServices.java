package com.example.guitar_center_android.Domain.Services.Implementation;

import android.content.Context;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Repository.Implementation.CartRepository;
import com.example.guitar_center_android.Repository.Interface.ICartRepository;

import java.util.List;

public class CartServices implements ICartServices {

    private ICartRepository cartRepository;
    public CartServices (Context context)
    {
        cartRepository = new CartRepository(context);
    }

    @Override
    public boolean insertCart(Product product) {
        return false;
    }

    @Override
    public boolean deleteCart(String id_Product) {
        return false;
    }

    @Override
    public boolean updateCart(Product product) {
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
