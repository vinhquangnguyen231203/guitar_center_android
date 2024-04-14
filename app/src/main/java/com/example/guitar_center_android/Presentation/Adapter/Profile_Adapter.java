package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;
import com.example.guitar_center_android.Domain.model.User;
import com.example.guitar_center_android.Presentation.Activity.ProfileActivity;
import com.example.guitar_center_android.R;
import com.google.gson.Gson;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Adapter {
    private Context context;
    private UserManager userManager;
    private EditText textViewFullname, textViewPassword, textViewPhone, textViewBirth, textViewAddress;

    public  Profile_Adapter(Context context, UserManager userManager){
        this.context = context;
        this.userManager = userManager;
    }
    public void getUserInfor(){

        //lay id giao dien
        textViewFullname = ((ProfileActivity) context).findViewById(R.id.txt_profile_fullname);
        textViewPassword = ((ProfileActivity) context).findViewById(R.id.txt_profile_password);
        textViewPhone = ((ProfileActivity) context).findViewById(R.id.txt_profile_phone);
        textViewBirth = ((ProfileActivity) context).findViewById(R.id.txt_profile_birth);
        textViewAddress = ((ProfileActivity) context).findViewById(R.id.txt_profile_address);


// Gọi hàm getUserInfor() từ UserManager sau khi đăng nhập thành công
        userManager.getUserInfor(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // Xử lý response từ server
                if (response.isSuccessful() && response.body() != null) {
                    // Hiển thị thông tin người dùng lên giao diện
                     User user = response.body();
                    // Hiển thị thông tin người dùng lên các EditText
                    textViewFullname.setText(user.getFullname());
                    textViewPassword.setText(user.getPassword());
                    textViewPhone.setText(user.getPhone());
                    // Chuyển đổi thuộc tính birth từ kiểu Date thành chuỗi định dạng ngày tháng
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    String birthDateString = dateFormat.format(user.getBirth());
                    textViewBirth.setText(birthDateString);
                    textViewAddress.setText(user.getAddress());

                } else {
                    Toast.makeText(context, "Lấy thông tin cá nhân thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Lấy thông tin cá nhân thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
