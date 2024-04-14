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
    //    @POST("orders/add")
    //    Call<OrderRequest> addOrder(@Body OrderRequest order);
    @POST("orders/{username}/add")
    Call<OrderRequest> addOrder(@Path("username") String username, @Body OrderRequest order);

    //người dùng xem danh sách đơn hàng của mình
    //    @GET("orders/my-orders")
    //    Call<List<Order>>  getAllMyOrders();
    @GET("orders/{username}/my-orders")
    Call<List<Order>>  getAllMyOrders(@Path("username") String username);

    //người dùng xem chi tiết đơn hàng
    //    @GET("orders/my-orders/:id")
    //    Call<List<OrderDetail>>  getOrderDetails(@Path("id") String id);
    @GET("orders/{username}/my-orders/{id}")
    Call<List<OrderDetail>>  getOrderDetails(@Path("username") String username,@Path("id") String id);


}