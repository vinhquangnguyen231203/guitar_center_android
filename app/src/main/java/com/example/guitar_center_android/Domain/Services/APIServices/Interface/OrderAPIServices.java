package com.example.guitar_center_android.Domain.Services.APIServices.Interface;

import com.example.guitar_center_android.Domain.model.Order;
import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.Domain.model.OrderRequest;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Path;

public interface OrderAPIServices {

    // người dùng đặt hàng
    @POST("orders/add")
    Call<OrderRequest> addOrder(@Body OrderRequest order);

    //người dùng xem danh sách đơn hàng của mình
    @GET("orders/my-orders")
    Call<List<Order>>  getAllMyOrders();

    //người dùng xem chi tiết đơn hàng
    @GET("orders/my-orders/:id")
    Call<List<OrderDetail>>  getOrderDetails(@Path("id") String id);

}