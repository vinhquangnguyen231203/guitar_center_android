package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.R;
import java.util.List;

public class OrderDetail_Adapter extends RecyclerView.Adapter<OrderDetail_Adapter.OrderDetailViewHolder>{
//
    private Context context;
    private List<OrderDetail> orderDetailList;
    private OrderManager orderManager;

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
        holder.textViewProductPrice.setText(orderDetail.getPrice());
        holder.textViewProductUnit.setText(orderDetail.getUnit());
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

    //----------- Load danh sách order details
    public void loadOrderDetails()
    {

    }

}
