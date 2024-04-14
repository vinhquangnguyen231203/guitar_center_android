package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.Presentation.Controller.Functions.ListCart;
import com.example.guitar_center_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartViewHolder> {
    //Instance Fields
    private Context context;
    private List<Product> productList;

    //Instance Fields cho xu ly sqlite
    private ICartServices cartServices;
    private CommandProcessor commandProcessor;


    //Constructor
    public Cart_Adapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public Cart_Adapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.CartViewHolder holder, int position) {
        Product product = productList.get(position);

        //set data vào views
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        holder.productUnit.setText(String.valueOf(product.getUnit()));


        //xử lý picasso
        String imagePath = "http://10.0.2.2:3333/api/products/"+product.getProductId()+"/image";
        Picasso.get()
                .load(imagePath)
//                .placeholder(R.drawable.placeholder_image) // Placeholder image khi đang tải
//                .error(R.drawable.error_image) // Ảnh lỗi nếu không tải được
                .into(holder.imgProduct);



    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public static  class CartViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName, productPrice, productTotalPrice, btnAdd, btnSub, productUnit,btnDelete;
        ImageView imgProduct;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName_cart);
            productPrice = itemView.findViewById(R.id.productPrice_cart);
            productTotalPrice = itemView.findViewById(R.id.productTotalPrice_cart);
            btnAdd = itemView.findViewById(R.id.btnAdd_cart);
            btnSub = itemView.findViewById(R.id.btnSub_cart);
            productUnit = itemView.findViewById(R.id.unit_cart);
            btnDelete = itemView.findViewById(R.id.btnDelete_cart);
        }
    }

    //-------------- LAY DU LIEU ICART SERVICES VÀ COMMANDPROCESSOR
    public void setICartServices(ICartServices services)
    {
        this.cartServices = cartServices;
    }
    public void setCommandProcessor(CommandProcessor commandProcessor)
    {
        this.commandProcessor = commandProcessor;
    }

    //----------- HÀM LOADCART
    public void loadCart()
    {
        productList = commandProcessor.getAllCart(new ListCart(cartServices));
        notifyDataSetChanged();
    }

}
