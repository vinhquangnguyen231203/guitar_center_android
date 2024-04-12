package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import androidx.annotation.NonNull;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.ProductAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.Retrofit_API;
import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManager {

    private final ProductAPIServices productAPIServices;

    public ProductManager() {
        productAPIServices = Retrofit_API.getRetrofitInstance().create(ProductAPIServices.class);
    }

    public void getAllProduct(final ListCallback callback) {
        Call<List<Product>> call = productAPIServices.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    String errorMessage = "Failed to get list of products";
                    if (response.errorBody() != null) {
                        errorMessage = response.errorBody().toString();
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface ListCallback {
        void onSuccess(List<Product> productList);

        void onFailure(String errorMessage);

    }


}
