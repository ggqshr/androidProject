package com.example.ggq.sqltest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ggq on 2017/4/6.
 */

public class DatabaseHelpse extends SQLiteOpenHelper {
    private final  static int vv=1;
    public DatabaseHelpse(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHelpse(Context context,String name)
    {
        this(context,name,vv);
    }
    public DatabaseHelpse(Context context,String name,int version)
    {
        this(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("create databse ");
        sqLiteDatabase.execSQL("create table user(id int,name varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("@@@@@@@@@@@@@@update database");
    }

}
