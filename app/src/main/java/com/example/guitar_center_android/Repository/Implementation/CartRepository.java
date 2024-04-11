package com.example.guitar_center_android.Repository.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Repository.DB_Helper.DB_Helper_Cart;
import com.example.guitar_center_android.Repository.Interface.ICartRepository;

import java.util.List;

public class CartRepository implements ICartRepository {

    private DB_Helper_Cart dbHelper;
    private SQLiteDatabase database;

    //Constructor
    public CartRepository(Context context){
        dbHelper = new DB_Helper_Cart(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public boolean insertCart(Product product) {
        ContentValues values = new ContentValues();
        values.
        return false;
    }

    @Override
    public boolean deleteCart(Product product) {
        return false;
    }

    @Override
    public boolean editCart(Product product) {
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
