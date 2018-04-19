package com.example.ggq.loginapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ggq on 2017/4/12.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    private final static int vv = 1;
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public SqliteHelper(Context context, String name) {
        this(context,name,vv);
    }
    public SqliteHelper(Context context, String name,int vvv) {
        this(context,name,null,vvv);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(number varchar(20),password varchar(20))");
        sqLiteDatabase.execSQL("insert into user(number,password) values('123','123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
