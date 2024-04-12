package com.example.guitar_center_android.Domain.Services.APIServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_API {
    private static final String BASE_URL = "http://10.0.2.2:3333/api/";

    public static Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }

}
