package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandCart;
import com.example.guitar_center_android.Repository.Interface.ICartRepository;

import java.util.List;

public class DeleteCart extends CommandCart {
    private String productId;

    //Constructor
    public DeleteCart(ICartServices cartServices, String productId)
    {
        super(cartServices);
        this.productId = productId;
    }

    @Override
    public boolean execute()
    {
        return cartServices.deleteCart(productId);
    }
    @Override
    public List<Product> getAllCart()
    {
        return  null;
    }
}
