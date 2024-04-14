package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.UserAPIServices;

import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;
import com.example.guitar_center_android.Domain.model.LoginRequest;
import com.example.guitar_center_android.Domain.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager {
    private final UserAPIServices userAPIServices;
    private Context context;
    public UserManager(Context context) {
        this.context = context;
        userAPIServices = RetrofitClient.getRetrofitInstance().create(UserAPIServices.class);
    }
//
    //đăng ký
    public void createUser(User user, Callback<User> callback) {
        Call<User> call = userAPIServices.createUser(user);
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("createUser_Json", new Gson().toJson(response.body()));
                    Toast.makeText(context, "Đăng ký thành công !",Toast.LENGTH_SHORT).show();
                    callback.onResponse(call, Response.success(response.body()));

                } else {


                    Toast.makeText(context, "Đăng ký thất bại !",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //đăng nhập
    public void login(String username, String password, Callback<User> callback) {
        Call<User> call = userAPIServices.loginUser(new LoginRequest(username,password));
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("login_Json",new Gson().toJson(response.body()));
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đăng nhập thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    //Xem thông tin cá nhân
//    public  void getUserInfor(Callback<User> callback){
//        Call<User> call = userAPIServices.getUserInfor();
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful() && response.body() != null) {
//
//                    callback.onResponse(call, Response.success(response.body()));
//                } else {
//                    Log.d("getUser_Json",new Gson().toJson(response.body()));
//
//                    Toast.makeText(context, "Lấy thông tin cá nhân thất bại in Manager !",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                callback.onFailure(call,t);
//            }
//        });
//    }

    public void getUserInfor(Callback<User> callback){
        // Gọi API để lấy thông tin người dùng
        Call<User> call = userAPIServices.getUserInfor();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Gọi callback onResponse() với thông tin người dùng
                    Log.d("getUserInfor_Json", new Gson().toJson(response.body()));
                    callback.onResponse(call, response);
                } else {
                    // Gọi callback onFailure() nếu không có dữ liệu hoặc lỗi
                    callback.onFailure(call, new Throwable("Lấy thông tin cá nhân thất bại!"));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Gọi callback onFailure() nếu xảy ra lỗi trong quá trình gọi API
                callback.onFailure(call, t);
            }
        });
    }

    //Sửa thông tin cá nhân
    public  void updateUserInfor(User user ,Callback<Response> callback){
        Call<Response> call = userAPIServices.updateUserInfor(user);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("updateInfor_Json", new Gson().toJson(response.body()));
                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Cập nhật thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Đăng xuất
    public void logouUser(Callback<Response> callback){
        Call<Response> call = userAPIServices.logoutUser();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("logout_Json", new Gson().toJson(response.body()));

                    callback.onResponse(call, Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đăng xuất thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}
