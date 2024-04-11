package com.example.guitar_center_android.Repository.Implementation;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guitar_center_android.Domain.model.Product;
import com.example.guitar_center_android.Repository.DB_Helper.DB_Helper_Cart;
import com.example.guitar_center_android.Repository.Interface.ICartRepository;

import java.util.ArrayList;
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
        // Thêm gio hàng vào sqlite
        // Prepared Statement giống ContentValues
        // values put theo key và value
        ContentValues values = new ContentValues();
        values.put("id_product",product.getProductId());
        values.put("name_product",product.getProductName());
        values.put("unit",product.getUnit());
        values.put("price",product.getPrice());
        values.put("image",product.getImage());
        values.put("categoryId",product.getCategoryId());
        values.put("description",product.getDescription());

        long result = database.insert("CART", null, values);

        // Kiểm tra có thêm thành công ko
        if (result != -1)
        {
            return  true;
        }
        return false;
    }

    @Override
    public boolean deleteCart(String id_Product) {
        // Xóa giỏ hàng khỏi sqlite
        // Xác định điều kiện xóa dựa trên id_product
        String selection = "id_product = ?";
        String[] selectionArgs = { id_Product };

        // Thực hiện xóa sản phẩm từ database
        int rowsDeleted = database.delete("CART", selection, selectionArgs);

        // Kiểm tra xem có sản phẩm nào được xóa không
        if (rowsDeleted > 0) {
            return true; // Xóa thành công
        }
        return false; // Không có sản phẩm nào được xóa
    }


    @Override
    public boolean updateCart(Product product) {
        ContentValues values = new ContentValues();
        values.put("name_product", product.getProductName());
        values.put("unit", product.getUnit());

        int rowsAffected = database.update("CART", values, "id_product = ?", new String[]{product.getId_product()});
        if(rowsAffected > 0)
        {
            return  true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        //ResultSet
        // Tạo 1 list chứa các dữ liệu
        List<Product> productList = new ArrayList<>();

        //Lệnh truy vấn
        String sql = "SELECT * FROM CART";
        Cursor cursor = database.rawQuery(sql, null);
        if (((Cursor) cursor).moveToFirst())
        {
            do {
                @SuppressLint("Range") Product product = new Product(
                    cursor.getString(cursor.getColumnIndex("id_product")),
                    cursor.getString(cursor.getColumnIndex("name_product")),
                    cursor.getInt(cursor.getColumnIndex("unit")),
                    cursor.getDouble(cursor.getColumnIndex("price")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("categoryId")),
                    cursor.getString(cursor.getColumnIndex("description"))
                );
                productList.add(product);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        //rs.close();
        return productList;
    }
}
