package com.example.guitar_center_android.Presentation.Controller.Command;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Repository.Interface.ICartRepository;

import java.util.List;

public abstract class CommandCart {
    protected ICartServices cartServices;

    public CommandCart(ICartServices cartServices)
    {
        this.cartServices = cartServices;
    }
    //Methods
    public abstract boolean execute();
    public abstract List<Product> getAllCart();
    public abstract Product getProduct();

}
