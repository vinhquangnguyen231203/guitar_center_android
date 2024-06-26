package com.example.guitar_center_android.Presentation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Presentation.Adapter.Singup_Adapter;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.R;

public class SignupActivity extends AppCompatActivity {

    private UserManager userManager;
    private Context context;
    private Singup_Adapter singupAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //-----------------Xu ly dang ky
        // Xu ly id
        Button btnSignup = findViewById(R.id.btn_register);

        // tạo mới Usermanager va singupAdapter
        userManager = new UserManager(this);

        singupAdapter = new Singup_Adapter(this, userManager);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singupAdapter.register();
            }
        });

        //-------------------Xu ly dieu huong
        //Xu ly id
        ImageView btnBackToLogin = findViewById(R.id.btn_backToLogin);

        //Thuc hien hanh dong dieu huong
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}
