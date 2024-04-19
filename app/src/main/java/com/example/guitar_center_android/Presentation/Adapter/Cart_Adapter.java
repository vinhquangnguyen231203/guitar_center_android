package com.example.guitar_center_android.Presentation.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.OrderManager;
import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.OrderBody;
import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Domain.model.User;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Activity.CartActivity;
import com.example.guitar_center_android.Presentation.Activity.LoginActivity;
import com.example.guitar_center_android.Presentation.Activity.MainActivity;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.Presentation.Controller.Functions.DeleteCart;
import com.example.guitar_center_android.Presentation.Controller.Functions.ListCart;
import com.example.guitar_center_android.Presentation.Controller.Functions.ListUser;
import com.example.guitar_center_android.Presentation.Controller.Functions.UpdateCart;
import com.example.guitar_center_android.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartViewHolder> {
    //Instance Fields
    private Context context;
    private List<Product> productList;

    //Instance Fields cho xu ly sqlite
    private ICartServices cartServices;
    private CommandProcessor commandProcessor;
    private int count;
    private TextView txtName, txtPhoneNumber, txtAddress, txtTotalPricePayment;


    private List<Integer> itemCountList;

    private IUserServices userServices;
    private String userName;
    private UserManager userManager;
    private OrderManager orderManager;

    //Constructor
    public Cart_Adapter(Context context) {
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
        Log.d("holder_product_json", product.toString());

        //set data vào views
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        holder.productUnit.setText(String.valueOf(product.getUnit()));
        holder.productTotalPrice.setText(this.totalPrice(product.getPrice(), product.getUnit()));

        //xử lý picasso
        String imagePath = "http://10.0.2.2:3333/api/products/" + product.getProductId() + "/image";
        if (imagePath != null) {
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
                notifyDataChanged_updateTotal();
                boolean checkValue = commandProcessor.executeCart(new UpdateCart(cartServices, product));
                if (checkValue) {
                    loadCart();
                } else {
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
                boolean checkValue = commandProcessor.executeCart(new UpdateCart(cartServices, product));
                if (checkValue) {
                    loadCart();
                } else {
                    Toast.makeText(context, "Ko thay đổi được giá trị giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //---------- XỬ LÝ XÓA SẢN PHẨM
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkResult = deleteCart_inSqlite(product.getProductId());
                if (checkResult) {
                    Toast.makeText(context, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
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

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productTotalPrice, btnAdd, btnSub, productUnit, btnDelete;
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
    public void setICartServices(ICartServices services) {
        this.cartServices = services;
    }

    public void setCommandProcessor(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    //----------- HÀM LOADCART TRONG SQLITE
    public void loadCart() {

        // --- Lỗi Null Expression
        productList = commandProcessor.getAllCart(new ListCart(cartServices));

        //Tạo 1 log
        for (Product product : productList) {
            Log.d("check_list_cart_json", product.toString());
        }

        //Lấy thông tin ngưoi dung hien thi trong thong tin thanh toan

        //Lay cac id
        txtName = ((CartActivity) context).findViewById(R.id.txtName_cart);
        txtPhoneNumber = ((CartActivity) context).findViewById(R.id.txtPhone_number_cart);
        txtAddress = ((CartActivity) context).findViewById(R.id.address_cart);
        MaterialButton btnPayment = ((CartActivity) context).findViewById(R.id.payment_cart_home);

        if(checkExitUser())
        {
            userManager.getUserInfor(userName, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    txtName.setText(user.getFullname());
                    txtPhoneNumber.setText(user.getPhone().toString());
                    txtAddress.setText(user.getAddress());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(context, "Không lấy dc thông tin user", Toast.LENGTH_SHORT).show();
                }
            });
        }
        notifyDataChanged_updateTotal();

    }

    //----- HÀM TÍNH TOTAL PRICE
    private String totalPrice(Double price, int unit) {
        return String.valueOf(price * unit);
    }


    //------ XỬ LÝ UPDATE SỐ LƯỢNG TRONG SQLITE
    private boolean deleteCart_inSqlite(String productId) {
        boolean checkResult = commandProcessor.executeCart(
                new DeleteCart(cartServices, productId)
        );
        return checkResult;
    }

    //-------XỬ LÝ XÓA SẢN PHẨM TRONG SQLITE
    private boolean updateCart_inSqlite(Product product, int count) {
        product.setUnit(count);
        boolean checkResult = commandProcessor.executeCart(
                new UpdateCart(cartServices, product)
        );
        return checkResult;
    }

    //---- Lấy các thông tin userServices và commandProcessor, orderManager
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public void setCommandProccessor(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }
    public void setOrderManager(OrderManager orderManager)
    {
        this.orderManager = orderManager;
    }

    //-- Hàm kiểm tra sqlite có user hay k
    public boolean checkExitUser() {
        List<UserSQL> userSQLList = commandProcessor.getAllUser(new ListUser(userServices));

        if(userSQLList.size() != 0)
        {
            for (UserSQL userSQL: userSQLList){
                userName = userSQL.getUserName();
            }
            return true;
        }
        return false;
    }

    //----- LAY THONG TIN USERMANGER TU CART ACTIVITY
    public void setUserManager(UserManager userManager)
    {
        this.userManager = userManager;
    }


    //--HANH DONG THANH TOAN
    public void payment()
    {
        if(checkExitUser())
        {
            show_Payment();
        }
        else
        {
            this.showLogin_not_user();
        }
    }

    //Kiểm tra thong tin
    private boolean checkInfoisValid() {
        List<UserSQL> userSQLList = userServices.getAllUser();

        if (userSQLList != null && !userSQLList.isEmpty()) {
            return true;
        }
        return false;
    }

    //-----HOP THOAI XU LY KHI CHUA DANG NHAP
    private void showLogin_not_user()
    {
        //Hiện hộp thoại hỏi
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Tiêu đề và nội dung cho hộp thoại
        builder.setTitle("Login để thanh toán");
        builder.setMessage("Bạn có muốn đăng nhập để thanh toán không");

        //Nút đồng ý cho hộp thoại
        // Khi đồng ý sẽ chuyển hướng
        //Khi ko sẽ ở lại page cart
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Xử lý thanh toán
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });

        //Khi ấn ko thì sẽ ở lại page
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing =)))))))
            }
        });

        builder.show();

    }
    private void show_Payment()
    {
        //Hiện hộp thoại hỏi
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Đặt tiêu đề và nội dung cho hộp thoại
        builder.setTitle("Thanh toán");
        builder.setMessage("Bạn có muốn thanh toán không ?");

        //Khi ấn đồng ý
        builder.setPositiveButton("Thanh toán", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(productList.size() == 0)
                {
                    Toast.makeText(context, "Không có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    do_payment();
                }
            }
        });

        //Khi ấn không
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

        builder.show();
    }

    //--- XU LY CAP NHAT SO LUONG
    private void updateTotalPrice()
    {
        txtTotalPricePayment = (((CartActivity)context).findViewById(R.id.txt_totalPricePayment_Cart));
        double totalPrice = 0;
        for (Product product : productList) {
            totalPrice += product.getPrice() * product.getUnit();
        }

        txtTotalPricePayment.setText(String.valueOf(totalPrice));
    }

    private void notifyDataChanged_updateTotal()
    {
        notifyDataSetChanged();
        updateTotalPrice();
    }

    private void do_payment()
    {
        //Lấy thông tin address và phoneNumber
        EditText txtPhoneNumber = ((CartActivity)context).findViewById(R.id.txtPhone_number_cart);
        EditText txtAdress = ((CartActivity)context).findViewById(R.id.address_cart);

        //Lưu thông tin vào OrderBody.Order
        OrderBody.Order order = new OrderBody.Order(txtAddress.toString(),txtPhoneNumber.toString());
        List<OrderBody.OrderDetail> listOrderDetails = getListOrderDetails();

        OrderBody orderBody = new OrderBody(order,listOrderDetails);

        //Gọi orderManager để thêm order
        orderManager.addOrder(userName, orderBody, new Callback<OrderBody>() {
            @Override
            public void onResponse(Call<OrderBody> call, Response<OrderBody> response) {
                boolean checkResult = cartServices.deleteAllCart();

                if(checkResult)
                {
                    Toast.makeText(context, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    loadCart();
                }
                else
                {
                    Toast.makeText(context, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderBody> call, Throwable t) {
                Toast.makeText(context, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
            }
        });



    }
    private List<OrderBody.OrderDetail> getListOrderDetails()
    {
        List<OrderBody.OrderDetail> listOrderDetails = new ArrayList<>();
        OrderBody.OrderDetail orderDetail = new OrderBody.OrderDetail();
        for(Product product: productList)
        {
            orderDetail.setProductId(product.getProductId());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setUnit(product.getUnit());

            //Them orderDetail vào list
            listOrderDetails.add(orderDetail);
        }
        return listOrderDetails;
    }
}
