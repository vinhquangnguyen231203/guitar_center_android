package com.example.guitar_center_android.Domain.Services.Implementation;

import android.content.Context;

import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Repository.Implementation.UserRepository;
import com.example.guitar_center_android.Repository.Interface.IUserRepository;

import java.util.List;

public class UserServices implements IUserServices {
    private IUserRepository userRepository;
    public  UserServices(Context context)
    {
        userRepository = new UserRepository(context);
    }
    @Override
    public boolean insertUser(UserSQL user) {
        if(user != null)
        {
            return userRepository.insertUser(user);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userName) {
        if(!userName.isEmpty())
        {
            return userRepository.deleteUser(userName);
        }
        return false;
    }

    @Override
    public boolean updateUser(UserSQL user) {
        if(user!= null){
            return userRepository.updateUser(user);
        }
        return false;
    }

    @Override
    public List<UserSQL> getAllUser() {
        return userRepository.getAllUser();
    }
}
