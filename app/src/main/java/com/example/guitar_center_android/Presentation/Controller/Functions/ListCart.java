package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandCart;

import java.util.List;

public class ListCart extends CommandCart {
    public ListCart(ICartServices cartServices)
    {
        super(cartServices);
    }

    @Override
    public boolean execute()
    {
        return false;
    }

    @Override
    public List<Product> getAllCart()
    {
        return cartServices.getAllCart();
    }

    @Override
    public Product getProduct() {
        return null;
    }
}
