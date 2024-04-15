package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.OrderDetail;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Activity.OrderDetailsActivity;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.Presentation.Controller.Functions.ListUser;
import com.example.guitar_center_android.R;
import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.OrderViewHolder> {
    private Context context;
    private List<Order> orderList;
    private OrderManager orderManager;
    private IUserServices userServices;
    private CommandProcessor commandProcessor;
    private String username;

    public  Order_Adapter(Context context, OrderManager orderManager){
        this.context = context;
        this.orderManager = orderManager;
    }


    public static  class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView textViewOrderId, textViewOrderStatus, textViewOrderdate, textViewOrderTotalPrice;
        Button btnOrderDetail;
        public  OrderViewHolder(View itemView){
            super(itemView);
            textViewOrderId = itemView.findViewById(R.id.txt_orderId);
            textViewOrderdate = itemView.findViewById(R.id.txt_orderTime);
            textViewOrderStatus = itemView.findViewById(R.id.txt_orderStatus);
            textViewOrderTotalPrice = itemView.findViewById(R.id.txt_totalPrice);
            btnOrderDetail = itemView.findViewById(R.id.btn_viewOrderDetail);
        }


    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater là một lớp dùng để chuyển đổi XML file thành các View objects trong Android
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        //set data vào views của order
        holder.textViewOrderId.setText(order.getOrderId());
        holder.textViewOrderdate.setText(order.getOrderDate());
        holder.textViewOrderStatus.setText(order.getStatus());
        holder.textViewOrderTotalPrice.setText(String.valueOf(order.getTotalPrice()));

        //set sự kiện chuyển hướng vào order detail
        holder.btnOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                direct_to_orderDetails(order);
            }
        });
    }

    public int getItemCount() {
        //trả về số lượng phần tử trong mảng
        return orderList == null ? 0 : orderList.size();
    }

    //---------HAM LAY IUserServices va CommandProcessor
    public void setIUserServices(IUserServices userServices)
    {
        this.userServices = userServices;
    }
    public  void setICommandProcessor(CommandProcessor commandProcessor)
    {
        this.commandProcessor = commandProcessor;
    }


    //load danh sách order
    public void loadOrder(){
        List<UserSQL> userSQLList = commandProcessor.getAllUser(new ListUser(userServices));

        for(UserSQL userSQL: userSQLList)
        {
            username = userSQL.getUserName();
        }
        orderManager.getAllMyOrders(username, new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orderList = response.body();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(context, "Lấy danh sách thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //chuyển hướng đến orderDetails
    private  void direct_to_orderDetails(Order order ){
        //tạo intent
        Intent intent = new Intent(context, OrderDetailsActivity.class);

        //đính kèm dữ liệu cần gửi
        intent.putExtra("ORDER_ID", order.getOrderId());
        intent.putExtra("USERNAME",username);

        context.startActivity(intent);
    }

}
