package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.R;

public class OrderDetailsActivity extends AppCompatActivity {
private Intent intent;
private TextView txtOrderId, txtOrderdate ,txtOrderFullname, txtOrderPhone, txtOrderStatus, txtOrderAddress, txtOrderTotalPrice, btnBacktoOrder;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        //lấy dữ liệu từ intent
        intent = getIntent();

        String orderId = intent.getStringExtra("ORDER_ID");

        //lấy các id
        txtOrderId = findViewById(R.id.txt_orderDetail_orderId);
        txtOrderdate = findViewById(R.id.txt_orderDetail_orderTime);
        txtOrderFullname = findViewById(R.id.txt_orderDetail_fullname);
        txtOrderPhone = findViewById(R.id.txt_orderDetail_phone);
        txtOrderStatus = findViewById(R.id.txt_orderDetail_status);
        txtOrderAddress = findViewById(R.id.txt_orderDetail_address);
        txtOrderTotalPrice = findViewById(R.id.txt_orderDetail_totalPrice);
        btnBacktoOrder = findViewById(R.id.btn_backToOrder);


        //Hiển thị thông Order màu hồng


    }

}
