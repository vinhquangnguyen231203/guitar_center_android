package com.example.guitar_center_android.Domain.Services.APIServices.Interface;

import com.example.guitar_center_android.Domain.model.Category;
import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.Domain.model.OrderRequest;
import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryAPIServices {

    // người dùng xem danh sách category
    @GET("categories")
    Call<List<Category>> getAllCategory();

    //người dùng xem sản phẩm trong category
    @GET("categories/:id")
    Call<List<Product>>  getProductInCategory(@Path("id") String id);

}