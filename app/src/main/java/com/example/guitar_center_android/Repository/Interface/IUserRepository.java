package com.example.guitar_center_android.Repository.Interface;

import com.example.guitar_center_android.Domain.model.UserSQL;

import java.util.List;

public interface IUserRepository {
    boolean insertUser(UserSQL user);
    boolean deleteUser(String username);
    boolean updateUser(UserSQL user);
    List<UserSQL> getAllUser();
}
