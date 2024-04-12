package com.example.guitar_center_android.Presentation.Controller.Command;

import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.UserSQL;

import java.util.List;

public abstract class CommandUser {
    protected IUserServices userServices;
    public CommandUser(IUserServices userServices)
    {
        this.userServices = userServices;
    }
    public abstract boolean execute();
    public abstract List<UserSQL> getAllUser();
}
