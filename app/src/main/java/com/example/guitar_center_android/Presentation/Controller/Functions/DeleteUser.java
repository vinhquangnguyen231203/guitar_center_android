package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandUser;

import java.util.List;

public class DeleteUser extends CommandUser {
    private String username;
    public DeleteUser(IUserServices userServices, String username)
    {
        super(userServices);
        this.username = username;
    }
    @Override
    public boolean execute()
    {
        return userServices.deleteUser(username);
    }

    @Override
    public List<UserSQL> getAllUser()
    {
        return null;
    }
}
