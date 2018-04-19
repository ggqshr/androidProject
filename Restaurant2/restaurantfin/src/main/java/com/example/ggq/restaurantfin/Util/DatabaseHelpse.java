package com.example.ggq.restaurantfin.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ggq on 2017/4/6.
 */

public class DatabaseHelpse extends SQLiteOpenHelper {
    private final static int vv = 1;

    public DatabaseHelpse(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelpse(Context context, String name) {
        this(context, name, vv);
    }

    public DatabaseHelpse(Context context, String name, int version) {
        this(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Cart(cartNumber varchar(20)," +
                "cartName varchar(20" +
                "cartPhoto varchar(20)" +
                "cartSum int" +
                "cartPrice varchar(20)" +
                "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
