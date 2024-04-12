package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandUser;

import java.util.List;

public class ListUser extends CommandUser {

    public ListUser(IUserServices userServices)
    {
        super(userServices);
    }
    @Override
    public boolean execute()
    {
        return  false;

    }
    @Override
    public List<UserSQL> getAllUser()
    {
        return null;
    }
}
