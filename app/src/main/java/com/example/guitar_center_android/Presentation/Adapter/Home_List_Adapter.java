package com.example.guitar_center_android.Presentation.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.ProductManager;
import com.example.guitar_center_android.Domain.model.Category;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Activity.DetailsActivity;
import com.example.guitar_center_android.Presentation.Activity.MainActivity;
import com.example.guitar_center_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_List_Adapter extends RecyclerView.Adapter<Home_List_Adapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;
    private ProductManager productManager;

    public  Home_List_Adapter(Context context,ProductManager productManager){
        this.context = context;
        this.productManager = productManager;
    };
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home, parent, false);
        return new ProductViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        //set data vào views

        holder.textViewName.setText(product.getProductName());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        //xử lý picasso
        String imagePath = "http://10.0.2.2:3333/api/products/"+product.getProductId()+"/image";
        Picasso.get()
                .load(imagePath)
//                .placeholder(R.drawable.placeholder_image) // Placeholder image khi đang tải
//                .error(R.drawable.error_image) // Ảnh lỗi nếu không tải được
                .into(holder.imageView);

        //set click listenerts for image
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dicrect_to_details(product);
            }
        });
    }



    @Override
    public int getItemCount() {
        //trả về số lượng phần tử trong mảng
        return productList == null ? 0 : productList.size();
    }

    public  static  class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  textViewName, textViewPrice;

        public ProductViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imgProduct_home_list);

            textViewName = itemView.findViewById(R.id.txtProductName_home_list);
            textViewPrice = itemView.findViewById(R.id.txtProductPrice_home_list);

        }
    }

    // --------- XỬ LÝ Ở ĐÂY -------

    //Load sản pham len trang home
    public void loadProduct()
    {
        productManager.getAllProduct(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Chuyển hướng đến ProductDetails
    private void  dicrect_to_details(Product product)
    {
        //Tạo intent
        Intent intent = new Intent(context,DetailsActivity.class);

        //Đính kèm dữ liệu cần gửi
        intent.putExtra("PRODUCT_ID",product.getProductId());
        intent.putExtra("PRODUCT_NAME",product.getProductName());
        intent.putExtra("PRODUCT_PRICE",product.getPrice());
        intent.putExtra("PRODUCT_DESCRIPTION",product.getDescription());

        context.startActivity(intent);
    }

    //Hiển thị product qua category
    public void loadProductByCategory(String categoryID)
    {
        productManager.getProductInCategory(categoryID, new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void  searchProduct(String text){
//
//        String searchText = text.toLowerCase().trim();
//        for (Product product : productList) {
//            if (product.getProductName().toLowerCase().contains(searchText)) {
//                productList.add(product);
//            }
//        }
//    }
    public List<Product> searchProduct(String text){
        List<Product> searchResult = new ArrayList<>();
        String searchText = text.toLowerCase().trim();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchText)) {
                searchResult.add(product);
            }
        }
        return searchResult;
    }

    //cập nhật dnah saách sản phẩm
    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
}

