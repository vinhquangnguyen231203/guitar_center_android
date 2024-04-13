package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    //Instance Fields
    private Intent intent;
    private TextView txtBack,txtProductName,txtProductPrice,txtProductDescription;

    private ImageView imgProduct;

    private MaterialButton btnAddCart;

    private ImageButton btnSub, btnAdd;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        //Lấy dữ liệu từ intent
        intent = getIntent();

        String productId = intent.getStringExtra("PRODUCT_ID");
        String productName = intent.getStringExtra("PRODUCT_NAME");
        Double productPrice = intent.getDoubleExtra("PRODUCT_PRICE",0);
        String productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION");


        //Lấy các id
        txtBack = findViewById(R.id.returnHome_details);
        txtProductName = findViewById(R.id.txtProductName_details);
        txtProductPrice = findViewById(R.id.txtProductPrice_details);
        txtProductDescription = findViewById(R.id.txtDescription_details);
        imgProduct = findViewById(R.id.imgProduct_details);
        btnAdd = findViewById(R.id.btnAdd_details);
        btnSub = findViewById(R.id.btnSub_details);

        //Hiển thị thông tin sản phẩm
        txtProductName.setText(productName);
        txtProductPrice.setText(productPrice.toString());
        txtProductDescription.setText(productDescription);

        //Xử lý ảnh bằng Picasso
        String imagePath = "http://10.0.2.2:3333/api/products/"+productId+"/image";
        Picasso.get()
                .load(imagePath)
//                .placeholder(R.drawable.placeholder_image) // Placeholder image khi đang tải
//                .error(R.drawable.error_image) // Ảnh lỗi nếu không tải được
                .into(imgProduct);

        //Thực hiện quay về trang chủ
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo intent và chuyển activity cho main
                intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
