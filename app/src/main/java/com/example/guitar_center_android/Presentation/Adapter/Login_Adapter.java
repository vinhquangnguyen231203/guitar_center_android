package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Presentation.Activity.LoginActivity;
import com.example.guitar_center_android.Presentation.Activity.MainActivity;
import com.example.guitar_center_android.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Adapter {
    private Context context;
    private UserManager userManager;
    private TextView textViewUsername, textViewPassword;

    private Button buttonLogin;
    public  Login_Adapter(Context context, UserManager userManager){
        this.context = context;
        this.userManager = userManager;
    }

    public void login(){
        //lấy id của giao diện
        textViewUsername = ((LoginActivity) context).findViewById(R.id.txt_login_username);
        textViewPassword = ((LoginActivity) context).findViewById(R.id.txt_login_password);

        //gán biến
        String username = textViewUsername.getText().toString();
        String password = textViewPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            userManager.login(username, password, new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, Response<Response> response) {

                    Log.d("login_Json",new Gson().toJson(response.body().toString()));
                    // Chuyển hướng đến trang home nếu đăng nhập thành công
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context, "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
