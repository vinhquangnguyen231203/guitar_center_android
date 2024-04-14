package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandCart;

import java.util.List;

public class DeleteAllCart extends CommandCart {

    public DeleteAllCart(ICartServices cartServices)
    {
        super(cartServices);
    }
    @Override
    public boolean execute() {
        return cartServices.deleteAllCart();
    }

    @Override
    public List<Product> getAllCart() {
        return null;
    }
}
