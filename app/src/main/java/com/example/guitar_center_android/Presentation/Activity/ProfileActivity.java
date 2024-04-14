package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Presentation.Adapter.Profile_Adapter;
import com.example.guitar_center_android.R;

public class ProfileActivity extends AppCompatActivity {
    private  UserManager userManager;
    private Profile_Adapter profileAdapter;
    private Button btnUpdateProfile ;
    private ImageView btnBackToHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo UserManager và Profile_Adapter
         userManager = new UserManager(this); // Thay UserManager bằng cách khởi tạo thực tế của bạn
        profileAdapter = new Profile_Adapter(this, userManager);
        // Gọi hàm getUserInfor từ Profile_Adapter
        profileAdapter.getUserInfor();

        //Lấy id của các button
    btnUpdateProfile = findViewById(R.id.btn_updateProfile);
    btnBackToHome = findViewById(R.id.btn_profile_toHome);

    //Cập nhật thông tin khi nhấn vào nút cập nhật
    btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //cập nhật thông tin người dùng
            profileAdapter.updateUserInfor();

            //load lại thông tin người dùng sau khi cập nhật
            profileAdapter.getUserInfor();
        }
    });
    // quay trở về trang home
    btnBackToHome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });
    }
}
