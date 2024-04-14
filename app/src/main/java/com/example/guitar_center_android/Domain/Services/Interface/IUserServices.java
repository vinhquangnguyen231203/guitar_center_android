package com.example.guitar_center_android.Domain.Services.Interface;

import com.example.guitar_center_android.Domain.model.User;
import com.example.guitar_center_android.Domain.model.UserSQL;

import java.util.List;

public interface IUserServices {
    boolean insertUser(UserSQL user);
    boolean deleteUser(String userName);
    boolean updateUser(UserSQL user);
    List<UserSQL> getAllUser();
    boolean deleteAllUser();
}
