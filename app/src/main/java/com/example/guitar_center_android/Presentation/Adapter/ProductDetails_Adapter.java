package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.ProductManager;
import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.Presentation.Controller.Functions.GetProductById;
import com.example.guitar_center_android.Presentation.Controller.Functions.InsertCart;
import com.example.guitar_center_android.Presentation.Controller.Functions.UpdateCart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails_Adapter {
    //Instance Fields
    private ICartServices cartServices;
    private CommandProcessor commandProcessor;
    private Context context;

    private ProductManager productManager;

    //CONSTRUCTOR
    public ProductDetails_Adapter(Context context, ProductManager productManager)
    {
        this.context = context;
        this.productManager = productManager;
    }

    //CONSTRUCTOR rỗng
    public  ProductDetails_Adapter()
    {

    }

    //------Lay cartServices và commandProcessor
    public  void setCartServices(ICartServices cartServices)
    {
        this.cartServices = cartServices;
    }
    public  void setCommandProcessor(CommandProcessor commandProcessor){
        this.commandProcessor = commandProcessor;
    }

    //--------Xử lý thêm sản phẩm vào giỏ hàng (sqlite)
    public void insertProductToCart(String productID, int unit)
    {

        //Kiểm tra nếu unit = 0 báo lỗi - ngược lại thì thêm
        if(unit == 0)
        {
            Toast.makeText(context, "Số lượng sản phẩm phải lớn hơn 0", Toast.LENGTH_SHORT).show();
        }
        else{
            //Lấy sản phẩm theo id bằng productManager
            productManager.getProductById(productID, new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    Product product = response.body();


                    Log.d("ShowProduct",product.toString());

                    // Thêm sản phẩm vào table Cart trong sqlite
                    // Kiểm tra nếu id sản phẩm tồn tại trong sqlite thì gọi hàm update()
                    // Ngược lại gọi hàm insert
                    Product productToCheck = commandProcessor.getProduct(new GetProductById(cartServices,productID));

                    if(productToCheck == null)
                    {
                        boolean checkResult = commandProcessor.executeCart(
                                new InsertCart(cartServices,product)
                        );
                        if(checkResult){
                            Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        product.setUnit(product.getUnit() + productToCheck.getUnit());
                        boolean checkResult = commandProcessor.executeCart(
                            new UpdateCart(cartServices,product)
                        );
                        if(checkResult){
                            Toast.makeText(context, "Cập nhật số lượng sản phẩm", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Cập nhật số lượng sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
