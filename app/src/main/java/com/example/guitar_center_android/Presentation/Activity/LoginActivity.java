package com.example.guitar_center_android.Presentation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.Presentation.Adapter.Login_Adapter;
import com.example.guitar_center_android.R;

public class LoginActivity extends AppCompatActivity {
    private UserManager userManager;
    private Login_Adapter loginAdapter;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        Button btnLogin = findViewById(R.id.btn_login);

        //tạo mới Usermanager
        userManager = new UserManager(this);

        loginAdapter = new Login_Adapter(this,userManager);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdapter.login();
            }
        });

        //nút trở về trang home
        ImageView btnBackToLogin = findViewById(R.id.txt_login_back);
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //vào trang đăng ký
        TextView btnSignup = findViewById(R.id.txt_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
