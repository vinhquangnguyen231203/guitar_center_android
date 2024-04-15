package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.Presentation.Controller.Functions.DeleteCart;
import com.example.guitar_center_android.Presentation.Controller.Functions.ListCart;
import com.example.guitar_center_android.Presentation.Controller.Functions.UpdateCart;
import com.example.guitar_center_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartViewHolder> {
    //Instance Fields
    private Context context;
    private List<Product> productList;

    //Instance Fields cho xu ly sqlite
    private ICartServices cartServices;
    private CommandProcessor commandProcessor;
    private  int count;

    private List<Integer> itemCountList;


    //Constructor
    public Cart_Adapter(Context context)
    {
        this.context = context;
        this.itemCountList = new ArrayList<>();
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

        // Check product List
        Log.d("holder_product_json",product.toString());

        //set data vào views
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        holder.productUnit.setText(String.valueOf(product.getUnit()));
        holder.productTotalPrice.setText(this.totalPrice(product.getPrice(),product.getUnit()));

        //xử lý picasso
        String imagePath = "http://10.0.2.2:3333/api/products/"+product.getProductId()+"/image";
        if(imagePath != null)
        {
            Picasso.get()
                    .load(imagePath)
//                    .placeholder(R.drawable.plus) // Placeholder image khi đang tải
//                    .error(R.drawable.plus) // Ảnh lỗi nếu không tải được
                    .into(holder.imgProduct);
        }


        //------------ XỬ LÝ TĂNG GIẢM SẢN PHẨM
        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giảm số lượng sản phẩm khi nhấn nút trừ
                //lấy số lượng hiện tại
                int currentQuantity = Integer.parseInt(holder.productUnit.getText().toString());
                if (currentQuantity > 0) {
                    // số lượng lớn hơn 0 thì giảm đi
                    currentQuantity--;
                    holder.productUnit.setText(String.valueOf(currentQuantity));
                }

                //Xu ly gia tri ben sqlite
                product.setUnit(currentQuantity);
                boolean checkValue = commandProcessor.executeCart(new UpdateCart(cartServices,product));
                if(checkValue)
                {
                    loadCart();
                }
                else
                {
                    Toast.makeText(context, "Ko thay đổi được giá trị giỏ hàng", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tăng số lượng sản phẩm khi nhấn nút cộng
                //lấy số lượng hiện tại
                int currentQuantity = Integer.parseInt(holder.productUnit.getText().toString());
                // tăng số lượng thêm 1
                currentQuantity++;
                holder.productUnit.setText(String.valueOf(currentQuantity));

                //Xu ly gia tri ben sqlite
                product.setUnit(currentQuantity);
                boolean checkValue = commandProcessor.executeCart(new UpdateCart(cartServices,product));
                if(checkValue)
                {
                    loadCart();
                }
                else
                {
                    Toast.makeText(context, "Ko thay đổi được giá trị giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //---------- XỬ LÝ XÓA SẢN PHẨM
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkResult = deleteCart_inSqlite(product.getProductId());
                if(checkResult)
                {
                    Toast.makeText(context, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
                Cart_Adapter.this.loadCart();

            }

        });

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
            imgProduct = itemView.findViewById(R.id.imgProduct_cart);
        }
    }

    //-------------- LAY DU LIEU ICART SERVICES VÀ COMMANDPROCESSOR
    public void setICartServices(ICartServices services)
    {
        this.cartServices = services;
    }
    public void setCommandProcessor(CommandProcessor commandProcessor)
    {
        this.commandProcessor = commandProcessor;
    }

    //----------- HÀM LOADCART TRONG SQLITE
    public void loadCart()
    {

        // --- Lỗi Null Expression
        productList = commandProcessor.getAllCart(new ListCart(cartServices));

        //Tạo 1 log
        for(Product product: productList)
        {
            Log.d("check_list_cart_json",product.toString());
        }

        notifyDataSetChanged();
    }

    //----- HÀM TÍNH TOTAL PRICE
    private String totalPrice(Double price, int unit)
    {
        return String.valueOf(price * unit);
    }


    //------ XỬ LÝ UPDATE SỐ LƯỢNG TRONG SQLITE
    private boolean deleteCart_inSqlite(String productId)
    {
        boolean checkResult = commandProcessor.executeCart(
                new DeleteCart(cartServices,productId)
        );
        return  checkResult;
    }

    //-------XỬ LÝ XÓA SẢN PHẨM TRONG SQLITE
    private boolean updateCart_inSqlite(Product product, int count)
    {
        product.setUnit(count);
        boolean checkResult = commandProcessor.executeCart(
                new UpdateCart(cartServices,product)
        );
        return  checkResult;
    }
}
