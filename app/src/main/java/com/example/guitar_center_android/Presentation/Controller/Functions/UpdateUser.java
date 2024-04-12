package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandUser;

import java.util.List;

public class UpdateUser extends CommandUser {
    private UserSQL userSQL;

    public UpdateUser (IUserServices userServices,UserSQL userSQL)
    {
        super(userServices);
        this.userSQL = userSQL;
    }

    //Methods
    @Override
    public boolean execute()
    {
        return userServices.updateUser(userSQL);
    }
    @Override
    public List<UserSQL> getAllUser()
    {
        return null;
    }
}
