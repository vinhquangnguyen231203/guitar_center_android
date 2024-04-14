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

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Adapter {
    private Context context;
    private UserManager userManager;
    private EditText textViewUsername,textViewFullname, textViewPassword, textViewPhone, textViewBirth, textViewAddress;

    public  Profile_Adapter(Context context, UserManager userManager){
        this.context = context;
        this.userManager = userManager;
    }

    public void getUserInfor(){

        //lay id giao dien
        textViewUsername = ((ProfileActivity) context).findViewById(R.id.txt_profile_username);
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
                    textViewUsername.setText(user.getUsername());
                    textViewFullname.setText(user.getFullname());
                    textViewPassword.setText(user.getPassword());
                    textViewPhone.setText(user.getPhone());
                    textViewBirth.setText(user.getBirth());
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

    public void updateUserInfor() {
        // Lấy thông tin mới từ EditText
        String username = textViewUsername.getText().toString();
        String fullname = textViewFullname.getText().toString();
        String password = textViewPassword.getText().toString();
        String phone = textViewPhone.getText().toString();
        String birth = textViewBirth.getText().toString();
        String address = textViewAddress.getText().toString();

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
        userManager.updateUserInfor(updatedUser, new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Xử lý khi cập nhật thành công
                    Toast.makeText(context, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                // Xử lý khi gặp lỗi trong quá trình cập nhật
                Toast.makeText(context, "Cập nhật thất bại: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
