package com.example.guitar_center_android.Domain.Services.APIServices.Interface;


import com.example.guitar_center_android.Domain.model.User;



import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface UserAPIServices {

    //người dùng đăng ký tài khoản
    @POST("users/register")
    Call<Boolean> createUser(@Body User user);

    // người dùng đăng nhập
    @POST("users")
    Call<Boolean>  loginUser(@Body String username, String password);

    // sau khi đăng nhập sẽ hiện thông tin người dùng
    @GET("users/me")
    Call<User>  getUserInfor();

    //người dùng chỉnh sửa thông tin hoặc thay đổi mật khẩu
    @PUT("users/update-infor")
    Call<Boolean>  updateUserInfor(@Body User user);

    // người dùng đăng xuất
    @GET("users/logout")
    Call<Boolean> logoutUser();
}