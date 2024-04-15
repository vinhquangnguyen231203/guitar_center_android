package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.Services.Implementation.UserServices;
import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Presentation.Adapter.Order_Adapter;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.R;

public class OrderStatusActivity extends AppCompatActivity {
    private OrderManager orderManager;
    private RecyclerView recyclerView;
    private Order_Adapter adapter;
    private Intent intent;

    private IUserServices userServices;
    private CommandProcessor commandProcessor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        //Khởi tạo Ordermanager
        orderManager = new OrderManager(this);

        // Khoi tao uservices and commandProcessor
        userServices = new UserServices(this);
        commandProcessor = new CommandProcessor();

        // khởi tọa recycleview và orderAdapter
        recyclerView = findViewById(R.id.order_list_view);
        adapter = new Order_Adapter(this, orderManager);
        adapter.setICommandProcessor(commandProcessor);
        adapter.setIUserServices(userServices);

        //set layout cho recycleview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //Tải danh sách sản phẩm từ API
        adapter.loadOrder();

        //Lấy các id
        Button btnHome = findViewById(R.id.btn_orderHistory_home);


        //---------------xử lý chuyển hướng về trang home
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderStatusActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }


}
