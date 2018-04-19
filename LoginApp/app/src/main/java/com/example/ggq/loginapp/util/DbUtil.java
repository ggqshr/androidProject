package com.example.ggq.loginapp.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ggq on 2017/4/12.
 */

public class DbUtil {
    public boolean Insert( Context context,User user)
    {
        String sql = " insert into user(number,password) values(?,?)";
        Object[] para = {
            user.getName(),
        user.getPassword()
        };
        SqliteHelper sqliteHelper = new SqliteHelper(context,"LoginTest");
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql,para);
        return true;
    }
    public User Query(Context context,String name)
    {
        System.out.println("!!!!!!");
        User user =null;
        String sql = "select * from user where number=? ";//where number=?";
        String[] para = new String[1];
        para[0] = name;
        SqliteHelper sqliteHelper = new SqliteHelper(context,"LoginTest");
        SQLiteDatabase s = sqliteHelper.getReadableDatabase();
        Cursor cursor = s.rawQuery(sql,para);
        while(cursor.moveToNext())
        {

            String number = cursor.getString(cursor.getColumnIndex("number"));
            if(number!=null)
            {
                System.out.println("@@@@@@221313");
                String password = cursor.getString(cursor.getColumnIndex("password"));
                user = new User(number,password);
            }
        }
        if(user!=null)
        {
            return user;
        }
        else
        {
            return null;
        }
    }
}
