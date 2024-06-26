package com.example.guitar_center_android.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.ProductManager;
import com.example.guitar_center_android.Domain.Services.Implementation.CartServices;
import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Presentation.Adapter.ProductDetails_Adapter;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    //Instance Fields
    private Intent intent;
    private TextView txtBack,txtProductName,txtProductPrice,txtProductDescription,txtProductUnit;

    private ImageView imgProduct;

    private MaterialButton btnAddCart;

    private ImageButton btnSub, btnAdd;

    private ProductManager productManager;
    private ICartServices cartServices;
    private CommandProcessor commandProcessor;

    private ProductDetails_Adapter productDetailsAdapter;
    private int count;

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
        txtProductUnit = findViewById(R.id.txtProductUnit_details);
        imgProduct = findViewById(R.id.imgProduct_details);
        btnAdd = findViewById(R.id.btnAdd_details);
        btnSub = findViewById(R.id.btnSub_details);
        btnAddCart = findViewById(R.id.btnAddCart_details);

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
        count = 0;
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo intent và chuyển activity cho main
                intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //---------Thực hiện tăng giảm số lượng
        //Tăng số lượng sản phẩm
        //---------Thực hiện tăng giảm số lượng
        //Tăng số lượng sản phẩm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
                txtProductUnit.setText(String.valueOf(count));
            }
        });

        //Giảm số lượng sản phẩm
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count <= 0)
                {
                    txtProductUnit.setText("0");
                }
                else
                {
                    count -= 1;
                    txtProductUnit.setText(String.valueOf(count));
                }
            }
        });

        //--------------- XỬ LÝ THÊM SẢN PHẨM VÀO GIỎ HÀNG

        //Tạo mới ProductManager
        productManager = new ProductManager(this);

        //Tạo mới đối tượng productDetails_Adapter
        productDetailsAdapter = new ProductDetails_Adapter(this,productManager);

        //Tạo mới đối tượng cartServices và commandProcessor
        cartServices = new CartServices(this);
        commandProcessor = new CommandProcessor();

        //Truyền cartServices và commandProcessor vào adapter
        productDetailsAdapter.setCartServices(cartServices);
        productDetailsAdapter.setCommandProcessor(commandProcessor);

        //Thực hiện hành động
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDetailsAdapter.insertProductToCart(productId,Integer.parseInt(txtProductUnit.getText().toString()));
            }
        });
    }

}
