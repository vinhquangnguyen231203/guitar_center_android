package com.example.guitar_center_android.Domain.Services.APIServices.Manager;


import android.content.Context;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.CategoryAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;

import com.example.guitar_center_android.Domain.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class CategoryManager {

    private final CategoryAPIServices categoryAPIServices;
    private Context context;
    public CategoryManager() {
        categoryAPIServices = RetrofitClient.getRetrofitInstance().create(CategoryAPIServices.class);
    }

    // người dùng xem danh sách category
    public void getAllCategory( Callback<List<Category>> callback) {
        Call<List<Category>> call = categoryAPIServices.getAllCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy danh sách sản phẩm thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

}
