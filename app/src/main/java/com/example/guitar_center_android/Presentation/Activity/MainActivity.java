package com.example.guitar_center_android.Presentation.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.guitar_center_android.Presentation.Adapter.Home_List_Adapter;
import com.example.guitar_center_android.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Đây là MainActivity
        Home_List_Adapter homeListAdapter = new Home_List_Adapter(this);
    }
}