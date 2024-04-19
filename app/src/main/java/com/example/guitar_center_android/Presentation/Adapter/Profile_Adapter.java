package com.example.guitar_center_android.Presentation.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Domain.Services.Interface.ICartServices;
import com.example.guitar_center_android.Domain.Services.Interface.IUserServices;
import com.example.guitar_center_android.Domain.model.User;
import com.example.guitar_center_android.Domain.model.UserSQL;
import com.example.guitar_center_android.Presentation.Activity.MainActivity;
import com.example.guitar_center_android.Presentation.Activity.ProfileActivity;
import com.example.guitar_center_android.Presentation.Controller.Command.CommandProcessor;
import com.example.guitar_center_android.Presentation.Controller.Functions.DeleteAllCart;
import com.example.guitar_center_android.Presentation.Controller.Functions.DeleteAllUser;
import com.example.guitar_center_android.Presentation.Controller.Functions.ListUser;
import com.example.guitar_center_android.R;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Adapter {
    private Context context;
    private UserManager userManager;
    private EditText textViewUsername,textViewFullname, textViewPassword, textViewPhone, textViewBirth, textViewAddress;

    private String userName;
    private IUserServices userServices;
    private CommandProcessor commandProcessor;
    private ICartServices cartServices;
    public  Profile_Adapter(Context context, UserManager userManager){
        this.context = context;
        this.userManager = userManager;
    }

    public void getUserInfor(){

        getUserName();

        //lay id giao dien
        textViewUsername = ((ProfileActivity) context).findViewById(R.id.txt_profile_username);
        textViewFullname = ((ProfileActivity) context).findViewById(R.id.txt_profile_fullname);
        textViewPassword = ((ProfileActivity) context).findViewById(R.id.txt_profile_password);
        textViewPhone = ((ProfileActivity) context).findViewById(R.id.txt_profile_phone);
        textViewBirth = ((ProfileActivity) context).findViewById(R.id.txt_profile_birth);
        textViewAddress = ((ProfileActivity) context).findViewById(R.id.txt_profile_address);

        //Lấy userName từ sqlite

        //Lấy thông tin tu username
        userManager.getUserInfor(userName, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                textViewUsername.setText(user.getUsername());
                textViewFullname.setText(user.getFullname());
                textViewPassword.setText(user.getPassword());
                textViewPhone.setText(user.getPhone());
                textViewBirth.setText(user.getBirth());
                textViewAddress.setText(user.getAddress());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateUserInfor() {
        //Lay id
        textViewUsername = ((ProfileActivity) context).findViewById(R.id.txt_profile_username);
        textViewFullname = ((ProfileActivity) context).findViewById(R.id.txt_profile_fullname);
        textViewPassword = ((ProfileActivity) context).findViewById(R.id.txt_profile_password);
        textViewPhone = ((ProfileActivity) context).findViewById(R.id.txt_profile_phone);
        textViewBirth = ((ProfileActivity) context).findViewById(R.id.txt_profile_birth);
        textViewAddress = ((ProfileActivity) context).findViewById(R.id.txt_profile_address);


        // Lấy thông tin mới từ EditText
        String username = textViewUsername.getText().toString();
        String fullname = textViewFullname.getText().toString();
        String password = textViewPassword.getText().toString();
        String phone = textViewPhone.getText().toString();
        String birth = textViewBirth.getText().toString();
        String address = textViewAddress.getText().toString();

        if(username == ""||fullname == ""||password == ""||phone == ""||birth == ""||address == ""){
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem ngày sinh có đúng định dạng không
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        dateFormat.setLenient(false); // Đặt cờ để kiểm tra ngày tháng năm chính xác

        Date birthDate;
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            Toast.makeText(context, "Vui lòng nhập ngày sinh đúng định dạng \"dd-MM-yyyy\"", Toast.LENGTH_SHORT).show();
            return; // Dừng hàm nếu ngày sinh không đúng định dạng
        }

        // Tạo user mới với thông tin mới
        User updatedUser = new User(username, password, fullname, phone, address, "", dateFormat.format(birthDate), "");

        // Gọi phương thức updateUserInfor từ UserManager

        getUserName();

        userManager.updateUserInfor(userName, updatedUser, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null && response.isSuccessful())
                {
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    getUserInfor();
                }
                else {
                    Toast.makeText(context, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //----------LAY DU LIEU CHO userServices, commandProcessor
    public void setIUserServices(IUserServices userServices)
    {
        this.userServices = userServices;
    }
    public void setCommandProcessor(CommandProcessor commandProcessor)
    {
        this.commandProcessor = commandProcessor;
    }
    public void setICartServices(ICartServices cartServices)
    {
        this.cartServices = cartServices;
    }

    //Xử lý logout
    public void logOut()
    {
        //Hiện thị thông báo hỏi

        //Tạo AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Tạo tiêu đề và thông báo cho alert này
        builder.setTitle("Thông báo đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");

        //Thiết lập sự kiện yes/no khi click
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi hàm chooseLogOut
                Profile_Adapter.this.chooseLogOut();
            }
        });

        //Thiết lập button no
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing =))
            }
        });

        //Tạo v hiển thị AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //---- Hàm cho sự kiện khi ấn yes
    private void chooseLogOut()
    {
        boolean checkResult = commandProcessor.executeUser(new DeleteAllUser(userServices));

        //Kiểm tra đã xóa dc chưa
        if (checkResult)
        {
            boolean checkCart = commandProcessor.executeCart(new DeleteAllCart(cartServices));
            if(checkResult)
            {
                //Chuyển hướng sang trang home
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);

                Toast.makeText(context,"Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Xảy ra lỗi hệ thống", Toast.LENGTH_SHORT).show();
            }


        }
        else{
            Toast.makeText(context, "Xảy ra lỗi hệ thống", Toast.LENGTH_SHORT).show();
        }


    }

    //-------- Ham lay username
    private void getUserName()
    {
        List<UserSQL> userSQLList = commandProcessor.getAllUser(new ListUser(userServices));

        for (UserSQL user: userSQLList)
        {
            userName = user.getUserName();
        }

    }


}
