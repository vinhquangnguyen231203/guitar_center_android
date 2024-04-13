package com.example.guitar_center_android.Presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.ProductManager;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Adapter.Home_List_Adapter;
import com.example.guitar_center_android.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // Instance Fields
    private ProductManager productManager;
    private RecyclerView recyclerView;
    private Home_List_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //Tạo mới product manager
        productManager = new ProductManager(this);

        //Khởi tạo recycle view và homeListAdapter
        recyclerView = findViewById(R.id.productListView_home);
        adapter = new Home_List_Adapter(this,productManager);

        // Set LayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Tải danh sách sản phẩm từ API
        adapter.loadProduct();

        ImageView bntSignUp = findViewById(R.id.iv_profile);
        bntSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}