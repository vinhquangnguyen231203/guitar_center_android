package com.example.guitar_center_android.Domain.Services.APIServices;

import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPIServices {

    @POST("users/register")
    Call<List<User>> createUser(@Body User user);

    @POST("users")
    Call<List<User>>  loginUser(@Body String username, String password);
    @GET("users/me")
    Call<User>  getUserInfor();
    @PUT("users/update-infor")
    Call<Void>  updateUserInfor(@Body User user);

    @GET("users/logout")
    Call<Void> logoutUser();
}