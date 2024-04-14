package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Domain.model.User;
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
            userManager.login(username, password, new Callback<Response<String>>() {
                @Override
                public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
//                    // Lưu session vào SharedPreferences sau khi đăng nhập thành công
//                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("username", username);
//                    // Lưu các thông tin khác nếu cần
//                    editor.apply();
//                    Toast.makeText(context, "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
//                    Log.d("login_Json",new Gson().toJson(response.body()));
//                    // Chuyển hướng đến trang home nếu đăng nhập thành công
//                    Intent intent = new Intent(context, MainActivity.class);
//                    context.startActivity(intent);

                    // Lưu session vào SharedPreferences sau khi đăng nhập thành công
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username); // Lưu tên người dùng
                    // Lưu các thông tin khác nếu cần
                    editor.apply();

                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển hướng đến trang home nếu đăng nhập thành công
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);

                }

                @Override
                public void onFailure(Call<Response<String>> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
