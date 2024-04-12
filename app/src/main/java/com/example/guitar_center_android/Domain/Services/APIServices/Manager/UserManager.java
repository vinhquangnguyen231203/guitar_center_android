package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import android.content.Context;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.UserAPIServices;

import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;
import com.example.guitar_center_android.Domain.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager {
    private final UserAPIServices userAPIServices;
    private Context context;
    public UserManager() {
        userAPIServices = RetrofitClient.getRetrofitInstance().create(UserAPIServices.class);
    }
//
    //đăng ký
    public void createUser(User user, Callback<Boolean> callback) {
        Call<Boolean> call = userAPIServices.createUser(user);
        call.enqueue(new Callback<Boolean>(){
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đăng ký thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //đăng nhập
    public void login(String username, String password, Callback<Boolean> callback) {
        Call<Boolean> call = userAPIServices.loginUser(username,password);
        call.enqueue(new Callback<Boolean>(){
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đăng nhập thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Xem thông tin cá nhân
    public  void getUserInfor(Callback<User> callback){
        Call<User> call = userAPIServices.getUserInfor();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy thông tin cá nhân thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Sửa thông tin cá nhân
    public  void updateUserInfor(User user ,Callback<Boolean> callback){
        Call<Boolean> call = userAPIServices.updateUserInfor(user);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Cập nhật thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Đăng xuất
    public void logouUser(Callback<Boolean> callback){
        Call<Boolean> call = userAPIServices.logoutUser();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đăng xuất thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}
