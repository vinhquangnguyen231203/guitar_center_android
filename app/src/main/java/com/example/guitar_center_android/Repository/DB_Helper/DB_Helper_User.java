package com.example.guitar_center_android.Repository.DB_Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper_User extends SQLiteOpenHelper {
    // Instance Fields
    private static final String DATABASE_NAME = "guitar_user.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DATABASE_User = "CREATE TABLE USER (" +
            "userName TEXT PRIMARY KEY," +
            "fullName TEXT)";

    //Constructor
    public DB_Helper_User(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE_User);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS USER");
        // Tạo lại bảng mới
        onCreate(db);
    }
}
