package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;

import java.util.List;

public class OrderDetail_Adapter {
//     extends RecyclerView.Adapter<OrderDetail_Adapter.OrderViewHolder>
    private Context context;
    private List<OrderDetail> orderDetailList;
    private OrderManager orderManager;

    public  OrderDetail_Adapter(Context context, OrderManager orderManager){
        this.context = context;
        this.orderManager = orderManager;
    }

    public static class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        TextView textViewProduct;
        public  OrderDetailViewHolder(View itemView){
            super(itemView);
        }
    }
}
