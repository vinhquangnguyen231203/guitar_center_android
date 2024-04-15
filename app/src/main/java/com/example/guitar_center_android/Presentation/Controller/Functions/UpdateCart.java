package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandCart;
import com.example.guitar_center_android.Repository.Interface.ICartRepository;

import java.util.List;

public class UpdateCart extends CommandCart {
    private Product product;
    public UpdateCart(ICartServices cartServices, Product product)
    {
        super(cartServices);
        this.product = product;
    }

    @Override
    public boolean execute()
    {
        return cartServices.updateCart(product);
    }
    @Override
    public List<Product> getAllCart()
    {
        return null;
    }

    @Override
    public Product getProduct() {
        return null;
    }
}
