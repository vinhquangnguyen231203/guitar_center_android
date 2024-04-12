package com.example.guitar_center_android.Domain.Services.APIServices.Interface;

import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Path;

public interface ProductAPIServices {

    // người dùng xem danh sach sản phẩm
    @GET("products")
    Call<List<Product>> getAllProducts();

    //Người dùng xem chi tiết sản phẩm
    @GET("products/{:id}")
    Call<Product>  getProductById(@Path("id") String id);

    //Lấy hình ảnh sản phẩm
    @GET("products/{:id}/image")
    Call<String> getProductImage(@Path("id") String id);

    //người dùng xem danh sách sản phẩm trong category
    @GET("products/category/{:id}")
    Call<List<Product>> getProductInCategory(@Path("id") String id);

}