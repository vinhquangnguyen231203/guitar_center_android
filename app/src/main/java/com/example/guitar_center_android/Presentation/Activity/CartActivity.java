package com.example.guitar_center_android.Presentation.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.Presentation.Adapter.Cart_Adapter;
import com.example.guitar_center_android.R;

public class CartActivity extends AppCompatActivity {
    //FIELDS
    private Cart_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_cart);
    }
}
