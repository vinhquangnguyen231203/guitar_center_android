package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Presentation.Adapter.Order_Adapter;
import com.example.guitar_center_android.R;

public class OrderStatusActivity extends AppCompatActivity {
    private OrderManager orderManager;
    private RecyclerView recyclerView;
    private Order_Adapter adapter;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        //Khởi tạo Ordermanager
        orderManager = new OrderManager(this);

        // khởi tọa recycleview và orderAdapter
        recyclerView = findViewById(R.id.order_list_view);
        adapter = new Order_Adapter(this, orderManager);


    //---------------xử lý chuyển hướng về trang home
        Button btnHome = findViewById(R.id.btn_orderHistory_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderStatusActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
