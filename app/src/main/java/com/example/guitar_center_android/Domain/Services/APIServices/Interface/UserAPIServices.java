package com.example.guitar_center_android.Domain.Services.APIServices.Interface;


import com.example.guitar_center_android.Domain.model.LoginRequest;
import com.example.guitar_center_android.Domain.model.User;



import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserAPIServices {

    //người dùng đăng ký tài khoản
    @POST("users/register")
    Call<User> createUser(@Body User user);

    // người dùng đăng nhập
    @POST("users")
    Call<User> loginUser(@Body LoginRequest loginRequest);



    // sau khi đăng nhập sẽ hiện thông tin người dùng
    // @GET("users/me")
    // Call<User>  getUserInfor();

    @GET("users/{username}")
    Call<User> getUserInfor(@Path("username") String username);

    //người dùng chỉnh sửa thông tin hoặc thay đổi mật khẩu
    // @PUT("users/update-infor")
    // Call<Response>  updateUserInfor(@Body User user);
    @PUT("users/{username}/update-infor")
    Call<User> updateUserInfor(@Path("username") String username , @Body User user);

    // người dùng đăng xuất
    @GET("users/logout")
    Call<Response> logoutUser();
}