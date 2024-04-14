package com.example.guitar_center_android.Presentation.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Presentation.Adapter.Profile_Adapter;
import com.example.guitar_center_android.R;

public class ProfileActivity extends AppCompatActivity {
    private  UserManager userManager;
    private Profile_Adapter profileAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo UserManager và Profile_Adapter
         userManager = new UserManager(this); // Thay UserManager bằng cách khởi tạo thực tế của bạn
        profileAdapter = new Profile_Adapter(this, userManager);
        // Gọi hàm getUserInfor từ Profile_Adapter
        profileAdapter.getUserInfor();

    }
}
