package com.example.guitar_center_android.Presentation.Controller.Command;

import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.UserSQL;

import java.util.List;

public class CommandProcessor {
    public boolean executeCart(CommandCart commandCart)
    {
        return commandCart.execute();
    }
    public boolean executeUser(CommandUser commandUser)
    {
        return commandUser.execute();
    }
    public List<Product> getAllCart(CommandCart commandCart)
    {
        return commandCart.getAllCart();
    }
    public List<UserSQL> getAllUser(CommandUser commandUser)
    {
        return commandUser.getAllUser();
    }
}
