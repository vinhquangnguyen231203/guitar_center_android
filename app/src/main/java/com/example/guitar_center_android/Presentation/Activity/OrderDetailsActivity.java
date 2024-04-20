package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.Services.APIServices.Manager.ProductManager;
import com.example.guitar_center_android.Domain.Services.Implementation.UserServices;
import com.example.guitar_center_android.Presentation.Adapter.OrderDetail_Adapter;
import com.example.guitar_center_android.R;

public class OrderDetailsActivity extends AppCompatActivity {
private TextView txtOrderId, txtOrderdate ,txtOrderFullname, txtOrderPhone, txtOrderStatus, txtOrderAddress, txtOrderTotalPrice, btnBacktoOrder;

private Intent intent;
private OrderDetail_Adapter adapter;
private OrderManager orderManager;
private ProductManager productManager;

private RecyclerView recyclerView;

private UserServices userServices;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        //lấy dữ liệu từ intent
        intent = getIntent();




        //Tạo mới OrderManager
        orderManager = new OrderManager(this);

        //Tao moi ProductManager
        productManager = new ProductManager(this);

        //Tao moi userServices
        userServices = new UserServices(this);

        //Tạo mới adapter
        adapter = new OrderDetail_Adapter(this,orderManager);

        //Truyền data vào trong adapter
        adapter.setIntent(intent);

        //Truyen ProductManger cho adapter
        adapter.setProductManager(productManager);

        //Truyen UserServices cho adapter
        adapter.setUserServices(userServices);


        //Recycle view add adapter
        recyclerView = findViewById(R.id.orderDetail_listView);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.loadOrderDetails();

        //------ FIND ID
        btnBacktoOrder = findViewById(R.id.btn_backToOrder);


        //---------XU LY CHUYEN HUONG
        //Quay ve trang order
        btnBacktoOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OrderDetailsActivity.this,OrderStatusActivity.class);
                startActivity(intent);
            }
        });


    }

}
