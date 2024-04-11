package com.example.guitar_center_android.Domain.Services.APIServices.Interface;

import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductAPIServices {
    @GET("products")
    Call<List<Product>> getAllProducts();

    @GET("products/{:id}")
    Call<Product>  getProductById(@Path("id") String id);

}