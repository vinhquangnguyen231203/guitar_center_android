package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import android.content.Context;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.ProductAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;
import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManager {
    private ProductAPIServices productAPIServices;
    private final Context context;
    public ProductManager(Context context)
    {
        productAPIServices = RetrofitClient.getRetrofitInstance().create(ProductAPIServices.class);
        this.context = context;
    }
    public void getAllProduct(Callback<List<Product>> callback)
    {
        Call<List<Product>> call = productAPIServices.getAllProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.body() != null || response.code() == 200)
                {
                    callback.onResponse(call,Response.success(response.body()));
                }
                else
                {
                    Toast.makeText(context, "Lấy danh sách sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });

    }
    public void getProductById(String productId,Callback<Product> callback)
    {
        Call<Product> call = productAPIServices.getProductById(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.body() != null || response.code() == 200)
                {
                    callback.onResponse(call,Response.success(response.body()));
                }
                else
                {
                    Toast.makeText(context, "Lấy sản phẩm theo id thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}
