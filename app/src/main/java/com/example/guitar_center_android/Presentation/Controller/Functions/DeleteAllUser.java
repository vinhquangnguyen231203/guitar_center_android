package com.example.guitar_center_android.Presentation.Controller.Functions;

import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandUser;

import java.util.List;

public class DeleteAllUser extends CommandUser {

    public DeleteAllUser(IUserServices userServices)
    {
        super(userServices);
    }
    @Override
    public boolean execute() {
        return userServices.deleteAllUser();
    }

    @Override
    public List<UserSQL> getAllUser() {
        return null;
    }
}
