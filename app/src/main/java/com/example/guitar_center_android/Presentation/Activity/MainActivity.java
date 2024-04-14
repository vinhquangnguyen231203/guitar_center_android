package com.example.guitar_center_android.Presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

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
    private Intent intent;

    private String categoryId;

    private ImageView c_piano,c_guitar, c_rum, c_trumpet, c_violin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Khởi chạy content View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----------- Xử lý load tất cả danh sách

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

        //-------------- Xử lý chuyển hướng
        // Lấy các id
        ImageView bntLogin = findViewById(R.id.img_profile);
        ImageView btnHome = findViewById(R.id.img_home);
        ImageView btnCart = findViewById(R.id.img_cart);
        ImageView btnOrder = findViewById(R.id.img_order);


        //Xử lý action chuyển hướng
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load lại all danh sách sản phẩm
                adapter.loadProduct();
            }
        });
        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direct(LoginActivity.class);
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direct(CartActivity.class);
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direct(OrderStatusActivity.class);
            }
        });

        //------------- Xử lý load theo danh mục
        //Lấy id của các category
        c_piano = findViewById(R.id.category_p);
        c_violin = findViewById(R.id.category_v);
        c_trumpet = findViewById(R.id.category_t);
        c_rum = findViewById(R.id.category_r);
        c_guitar = findViewById(R.id.category_g);

        //Xu ly su kien loc danh sach theo category
        c_piano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryId = "P";
                adapter.loadProductByCategory(categoryId);
            }
        });
        c_violin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryId = "V";
                adapter.loadProductByCategory(categoryId);
            }
        });
        c_guitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryId = "G";
                adapter.loadProductByCategory(categoryId);
            }
        });

        c_rum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryId = "R";
                adapter.loadProductByCategory(categoryId);
            }
        });

        c_trumpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryId = "T";
                adapter.loadProductByCategory(categoryId);
            }
        });

        //---------- Xu ly loc danh sach theo tu khoa tim kiem
        //----------- Nho quy dinh lai position cua item sau khi loc
        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                if(newText == null || newText.isEmpty()){
                    adapter.loadProduct();
                } else {
                    List<Product> searchResult = adapter.searchProduct(newText);
                    adapter.setProductList(searchResult);
                }
                return true;
            }
        });
    }

    //Hàm direct để chuyển hướng
    private void direct(Class cls)
    {
        intent = new Intent(MainActivity.this,cls);
        startActivity(intent);
    }

}