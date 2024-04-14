package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.Implementation.CartServices;
import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Presentation.Adapter.Cart_Adapter;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.R;
import com.google.android.material.button.MaterialButton;

public class CartActivity extends AppCompatActivity {
    //FIELDS
    private Cart_Adapter adapter;

    private ICartServices cartServices;
    private CommandProcessor commandProcessor;

    private RecyclerView recyclerView;
    private TextView txtName, btnBack;
    private EditText txtPhone, txtAdress;
    private MaterialButton btnPayment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_cart);

        //---- -------------Khởi tạo

        //Khởi tạo instance field cho sqlite
        cartServices = new CartServices(this);
        commandProcessor = new CommandProcessor();

        //Khởi tạo adapter
        adapter = new Cart_Adapter(this);
        adapter.setICartServices(cartServices);
        adapter.setCommandProcessor(commandProcessor);

        //-------------- Thêm adapater vào recycle view
        //Lấy id
        recyclerView = findViewById(R.id.recycleview_cart);
        txtName = findViewById(R.id.txtName_cart);
        txtAdress = findViewById(R.id.address_cart);
        btnBack = findViewById(R.id.back_cart_home);
        btnPayment = findViewById(R.id.payment_cart_home);

        //Load danh sách cart
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.loadCart();

        //--------- Xử lý chuyển hướng về home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
