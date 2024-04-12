package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import android.content.Context;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.OrderAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.Interface.ProductAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;

import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.Domain.model.OrderRequest;
import com.example.guitar_center_android.Domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderManager {

    private final OrderAPIServices orderAPIServices;
    private Context context;
    public OrderManager() {
        orderAPIServices = RetrofitClient.getRetrofitInstance().create(OrderAPIServices.class);
    }

    //Đặt hàng
    public  void addOrder(OrderRequest orderRequest, Callback<OrderRequest> callback){
        Call<OrderRequest> call = orderAPIServices.addOrder(orderRequest);
        call.enqueue(new Callback<OrderRequest>() {
            @Override
            public void onResponse(Call<OrderRequest> call, Response<OrderRequest> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đặt hàng thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderRequest> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Xem danh sách đơn hàng của người dùng
    public void getAllMyOrders(Callback<List<Order>> callback){
        Call<List<Order>> call = orderAPIServices.getAllMyOrders();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy đơn hàng thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Xem chi tiết đơn hàng
    public  void geOrderDetails(String id, Callback<List<OrderDetail>> callback){
        Call<List<OrderDetail>> call = orderAPIServices.getOrderDetails(id);
        call.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Lấy đơn hàng thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


}
