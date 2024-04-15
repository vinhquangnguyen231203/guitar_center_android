package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandCart;

import java.util.List;

public class GetProductById extends CommandCart {
    private String productId;
    public GetProductById(ICartServices cartServices, String productId)
    {
        super(cartServices);
        this.productId = productId;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public List<Product> getAllCart() {
        return null;
    }

    @Override
    public Product getProduct() {
        return cartServices.getProductById(productId);
    }
}
