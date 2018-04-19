package com.example.ggq.restaurantfin.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ggq.restaurantfin.entity.Cart;

/**
 * Created by ggq on 2017/5/26.
 */

public class DatabaseUtil {
    private String dbName = "Cart";

    public void insert(Context context, Cart cart) {
        String sql = "insert into Cart values(?,?,?,?,?)";
        DatabaseHelpse databaseHelpse = new DatabaseHelpse(context, dbName, 1);
        SQLiteDatabase database = databaseHelpse.getWritableDatabase();
        Object[] para = {
                cart.getCartNumber(), cart.getCartName(),
                cart.getCartPhoto(), cart.getCartSum(),
                cart.getCartPrice()
        };
        database.execSQL(sql, para);
        database.close();
    }

    public void delete(Context context, String cartNumber) {
        String sql = "delete from Cart where cartNumber =?";
        DatabaseHelpse databaseHelpse = new DatabaseHelpse(context, dbName);
        SQLiteDatabase database = databaseHelpse.getWritableDatabase();
        Object[] para = {
                cartNumber
        };
        database.execSQL(sql, para);
        database.close();
    }

    public void update(Context context, Cart cart) {
        String sql = "update Cart set cartSum=? where cartNumber=?";
        DatabaseHelpse databaseHelpse = new DatabaseHelpse(context, dbName);
        SQLiteDatabase database = databaseHelpse.getWritableDatabase();
        Object[] para = {
                cart.getCartSum(), cart.getCartNumber()
        };
        database.execSQL(sql, para);
        database.close();
    }

    public Cart queryAsNumber(Context context, String cartNumber) {
        DatabaseHelpse databaseHelpse = new DatabaseHelpse(context, dbName);
        Cart cart = null;
        SQLiteDatabase database = databaseHelpse.getReadableDatabase();
        String[] para = {
                cartNumber
        };
        Cursor cursor = database.rawQuery("select * from Cart where cartNumber=?", para);
        while (cursor.moveToNext()) {
            cart = new Cart();
            cart.setCartNumber(cursor.getString(cursor.getColumnIndex("cartNumber")));
            cart.setCartName(cursor.getString(cursor.getColumnIndex("cartName")));
            cart.setCartPhoto(cursor.getString(cursor.getColumnIndex("cartPhoto")));
            cart.setCartSum(cursor.getInt(cursor.getColumnIndex("cartSum")));
            cart.setCartPrice(cursor.getString(cursor.getColumnIndex("cartPrice")));
        }
        return cart;
    }

    public Cursor query(Context context) {
        DatabaseHelpse databaseHelpse = new DatabaseHelpse(context, dbName);
        SQLiteDatabase database = databaseHelpse.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from Cart", null);
        return cursor;
    }
}
