package com.example.guitar_center_android.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitar_center_android.Domain.model.User;
import com.example.guitar_center_android.Presentation.Activity.LoginActivity;
import com.example.guitar_center_android.Presentation.Activity.SignupActivity;
import com.example.guitar_center_android.R;

import com.example.guitar_center_android.Domain.Services.APIServices.Manager.UserManager;

import java.text.ParseException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Singup_Adapter {

    private Context context;
    private UserManager userManager;
   private TextView  textViewUsername, textViewPassword, textViewFullname, textViewAddress, textViewBirth, textViewPhone;
    private RadioButton radioButtonMale, radioButtonFemale;
    public Singup_Adapter(Context context, UserManager userManager){
        this.context = context;
        this.userManager = userManager;
    }

   public void register(){
        //lấy id cua giao diện

       textViewUsername = ((SignupActivity) context).findViewById(R.id.txt_register_username);
       textViewPassword = ((SignupActivity) context).findViewById(R.id.txt_register_password);
       textViewFullname = ((SignupActivity) context).findViewById(R.id.txt_register_fullname);
       textViewAddress = ((SignupActivity) context).findViewById(R.id.txt_register_address);
       textViewBirth = ((SignupActivity) context).findViewById(R.id.txt_register_birth);
       textViewPhone = ((SignupActivity) context).findViewById(R.id.txt_register_phone);

       radioButtonFemale = ((SignupActivity) context).findViewById(R.id.rb_female);
       radioButtonMale =  ((SignupActivity) context).findViewById(R.id.rb_male);

       //gán biến
       String username = textViewUsername.getText().toString();
       String password = textViewPassword.getText().toString();
       String fullname = textViewFullname.getText().toString();
       String address = textViewAddress.getText().toString();
       String birthString = textViewBirth.getText().toString();
       String phone = textViewPhone.getText().toString();

//       // Kiểm tra xem ngày sinh có đúng định dạng không
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
       dateFormat.setLenient(false); // Đặt cờ để kiểm tra ngày tháng năm chính xác
//       //khoi tạo birth mặc định là null
       Date birth = null;
       try {
           birth = dateFormat.parse(birthString);
       } catch (ParseException e) {
           Toast.makeText(context, "Vui lòng nhập ngày sinh đúng định dạng \"dd-MM-yyyy\"", Toast.LENGTH_SHORT).show();

       }
//        Kiểm tra nếu không nhập đủ thông tin
       if (username.isEmpty() ||
               password.isEmpty() ||
               fullname.isEmpty() ||
               address.isEmpty() ||
               birth == null||
               phone.isEmpty() ||
               (!radioButtonFemale.isChecked() && !radioButtonMale.isChecked())) {

           // Hiển thị thông báo
           Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
       }
       String gender = "M";
       if(radioButtonFemale.isChecked()){
            gender = "F";
       }
       if(radioButtonMale.isChecked()){
            gender = "M";
       }
        User user = new User(username,password,fullname, phone,address,gender , birth, "U");
       userManager.createUser(user, new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {

               // Chuyển hướng đến trang login nếu đăng ký thành công
               Intent intent = new Intent(context, LoginActivity.class);
               context.startActivity(intent);
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {
               Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
   }







}
