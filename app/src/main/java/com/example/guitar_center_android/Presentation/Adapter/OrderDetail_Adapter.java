package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.Services.APIServices.Manager.ProductManager;
import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetail_Adapter extends RecyclerView.Adapter<OrderDetail_Adapter.OrderDetailViewHolder>{
//
    private Context context;
    private List<OrderDetail> orderDetailList;
    private OrderManager orderManager;
    private ProductManager productManager;

    private  Intent intent;


    public  OrderDetail_Adapter(Context context, OrderManager orderManager){
        this.context = context;
        this.orderManager = orderManager;
    }



    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);

        //set data vao views

        holder.textViewProductName.setText(orderDetail.getOrderId());
        holder.textViewProductPrice.setText(String.valueOf(orderDetail.getPrice()));
        holder.textViewProductUnit.setText(String.valueOf(orderDetail.getUnit()));

        String productId = orderDetail.getProductId();

        productManager.getProductById(productId, new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();

                //Su dung picasso
                //xử lý picasso
                String imagePath = "http://10.0.2.2:3333/api/products/"+product.getProductId()+"/image";
                Picasso.get()
                        .load(imagePath)
//                .placeholder(R.drawable.placeholder_image) // Placeholder image khi đang tải
//                .error(R.drawable.error_image) // Ảnh lỗi nếu không tải được
                        .into(holder.imageViewProduct);

                holder.textViewProductName.setText(product.getProductName());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        //trả về số lượng phần tử trong mảng
        return orderDetailList == null ? 0 : orderDetailList.size();
    }

    public static class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        TextView textViewProductName, textViewProductPrice, textViewProductUnit;
        ImageView imageViewProduct;
        public  OrderDetailViewHolder(View itemView){
            super(itemView);

            imageViewProduct  = itemView.findViewById(R.id.img_orderDetai);
            textViewProductName = itemView.findViewById(R.id.txt_item_orderDetail_productName);
            textViewProductPrice = itemView.findViewById(R.id.txt_item_orderDetail_productPrice);
            textViewProductUnit = itemView.findViewById(R.id.txt_item_orderDetail_productUnit);


        }
    }

    //-------------- Lay intent tu OrderDetailsActivity
    public  void setIntent(Intent intent)
    {
       this.intent = intent;

    }
    public  void setProductManager(ProductManager productManager)
    {
        this.productManager = productManager;
    }

    //----------- Load danh sách order details
    public void loadOrderDetails()
    {
        String orderId =  intent.getStringExtra("ORDER_ID");
        String userName = intent.getStringExtra("USERNAME");

        orderManager.getOrderDetails(userName, orderId, new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                orderDetailList = response.body();

                //Đặt log check dữ liệu list

                for(OrderDetail orderDetail : orderDetailList)
                {
                    Log.d("Check_order_list",orderDetail.toString());
                }

                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                Toast.makeText(context, "Lấy chi tiết order thất bại", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
