package com.example.guitar_center_android.Domain.Services.APIServices.Manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Interface.OrderAPIServices;
import com.example.guitar_center_android.Domain.Services.APIServices.RetrofitClient;

import com.example.guitar_center_android.Domain.model.OrderBody;
import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderManager {

    private final OrderAPIServices orderAPIServices;
    private Context context;
    public OrderManager(Context context) {
        this.context = context;
        orderAPIServices = RetrofitClient.getRetrofitInstance().create(OrderAPIServices.class);
    }

    //Đặt hàng
    public  void addOrder(String userName, OrderBody orderBody, Callback<OrderBody> callback){
        Call<OrderBody> call = orderAPIServices.addOrder(userName, orderBody);
        call.enqueue(new Callback<OrderBody>() {
            @Override
            public void onResponse(Call<OrderBody> call, Response<OrderBody> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Log.d("addOrder_Json",response.body().toString());
                    callback.onResponse(call,Response.success(response.body()));
                } else {
                    Toast.makeText(context, "Đặt hàng thất bại !",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderBody> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    //Xem danh sách đơn hàng của người dùng
    public void getAllMyOrders(String userName,Callback<List<Order>> callback){
        Call<List<Order>> call = orderAPIServices.getAllMyOrders(userName);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Log.d("getAllMyOrders_Json",response.body().toString());
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
    public  void getOrderDetails(String userName, String id, Callback<List<OrderDetail>> callback){
        Call<List<OrderDetail>> call = orderAPIServices.getOrderDetails(userName,id);
        call.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Log.d("getOrderDetails_Json",response.body().toString());
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
