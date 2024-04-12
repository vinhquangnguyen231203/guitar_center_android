package com.example.guitar_center_android.Domain.Services.APIServices.Interface;

import com.example.guitar_center_android.Domain.model.Category;
import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;

public interface CategoryAPIServices {

    // người dùng xem danh sách category
    @GET("categories")
    Call<List<Category>> getAllCategory();

}