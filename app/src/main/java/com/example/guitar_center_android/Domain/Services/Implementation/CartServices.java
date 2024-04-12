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
        if(product != null)
        {
            return cartRepository.insertCart(product);
        }
        return false;
    }

    @Override
    public boolean deleteCart(String id_Product) {
        if(! id_Product.isEmpty())
        {
            return cartRepository.deleteCart(id_Product);
        }
        return false;
    }

    @Override
    public boolean updateCart(Product product) {
        if(product != null)
        {
            return cartRepository.updateCart(product);
        }
        return false;
    }

    @Override
    public List<Product> getAllCart() {
        return cartRepository.getAllCart();
    }
}
