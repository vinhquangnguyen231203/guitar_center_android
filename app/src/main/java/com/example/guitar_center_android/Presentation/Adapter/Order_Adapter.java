package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Activity.OrderDetailsActivity;
import com.example.guitar_center_android.R;
import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_Adapter {
    private Context context;
    private List<Order> orderList;
    private OrderManager orderManager;

    public  Order_Adapter(Context context, OrderManager orderManager){
        this.context = context;
        this.orderManager = orderManager;
    }


    public  OrderViewHolder onCreatViewHolder(@NonNull ViewGroup parent, int viewType){

        // LayoutInflater là một lớp dùng để chuyển đổi XML file thành các View objects trong Android
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    };

    public  void onBindViewHolder(OrderViewHolder holder, int position){
        Order order = orderList.get(position);

        //set data vào views của order
        holder.textViewOrderId.setText(order.getOrderId());
        holder.textViewOrderdate.setText(order.getOrderDate());
        holder.textViewOrderStatus.setText(order.getStatus());
        holder.textViewOrderTotalPrice.setText((int) order.getTotalPrice());
    }

    public int getItemCount() {
        //trả về số lượng phần tử trong mảng
        return orderList == null ? 0 : orderList.size();
    }

    public static  class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView textViewOrderId, textViewOrderStatus, textViewOrderdate, textViewOrderTotalPrice;

        public  OrderViewHolder(View itemView){
            super(itemView);
            textViewOrderId = itemView.findViewById(R.id.txt_orderId);
            textViewOrderdate = itemView.findViewById(R.id.txt_orderTime);
            textViewOrderStatus = itemView.findViewById(R.id.txt_orderStatus);
            textViewOrderTotalPrice = itemView.findViewById(R.id.txt_totalPrice);
        }


    }

    //load danh sách order
    public void loadOrder(){
        orderManager.getAllMyOrders(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orderList = response.body();

            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //chuyển hướng đến orderDetails
    private  void direct_to_orderDetails(List<Order> orderList ){
        //tạo intent
        Intent intent = new Intent(context, OrderDetailsActivity.class);



        context.startActivity(intent);
    }

}
