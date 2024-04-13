package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.ProductAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;
import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManager {

    private final ProductAPIServices productAPIServices;
    private Context context;
    public ProductManager(Context context) {
        this.context = context;
        productAPIServices = RetrofitClient.getRetrofitInstance().create(ProductAPIServices.class);
    }

    //Xem danh sách sản phẩm
    public void getAllProduct( Callback<List<Product>> callback) {
        Call<List<Product>> call = productAPIServices.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy danh sách sản phẩm thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Xem chi tiết sản phẩm
    public void getProductById(String productId, Callback<Product> callback) {
        Call<Product>call = productAPIServices.getProductById(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy sản phẩm thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Lấy url sản phẩm bằng productId
    public void getProductImage(String productId, Callback<String> callback) {
        Call<String>call = productAPIServices.getProductImage(productId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy ảnh sản phẩm thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    ///Xem danh sách sản phẩm trong category
    public void getProductInCategory(String categoryId, Callback<List<Product>> callback) {
        Call<List<Product>> call = productAPIServices.getProductInCategory(categoryId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy danh sách sản phẩm thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}
