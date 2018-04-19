package com.example.ggq.sqltest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ggq.sqltest.db.DatabaseHelpse;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button creatbar=(Button)findViewById(R.id.button6);
        Button updatabasebar=(Button)findViewById(R.id.button7);
        Button insertbar=(Button)findViewById(R.id.button);
        Button delebar=(Button)findViewById(R.id.button2);
        Button querybar=(Button)findViewById(R.id.button4);
        creatbar.setOnClickListener(new ss());
        updatabasebar.setOnClickListener(new sss());
        insertbar.setOnClickListener(new insertoc());
        delebar.setOnClickListener(new deleoc());
        querybar.setOnClickListener(new queryoc());
    }
    class ss implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            DatabaseHelpse databaseHelpse=new DatabaseHelpse(MainActivity.this,"sqlite_test");
            SQLiteDatabase database=databaseHelpse.getReadableDatabase();
            database.execSQL("create table test(id int,name char(20))");
        }
    }
    class sss implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            DatabaseHelpse databaseHelpse=new DatabaseHelpse(MainActivity.this,"sqlite_test",2);
            SQLiteDatabase sqLiteDatabase=databaseHelpse.getReadableDatabase();
        }
    }
    class insertoc implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            DatabaseHelpse databaseHelpse=new DatabaseHelpse(MainActivity.this,"sqlite_test",2);
            SQLiteDatabase database=databaseHelpse.getWritableDatabase();
//            Object[] para={
//                1,"ggq"
//            };
//            database.execSQL("insert into user(id,name) values(?,?)",para);
            ContentValues contentValues=new ContentValues();
            contentValues.put("id",2);
            contentValues.put("name","dps");
            database.insert("user",null,contentValues);
            database.close();
        }
    }
    class deleoc implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            DatabaseHelpse databaseHelpse=new DatabaseHelpse(MainActivity.this,"sqlite_test",2);
            SQLiteDatabase database=databaseHelpse.getWritableDatabase();
            Object[] para={
                    1
            };
            database.execSQL("delete from user where id =?",para);
            database.close();
        }
    }
    class queryoc implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            DatabaseHelpse databaseHelpse=new DatabaseHelpse(MainActivity.this,"sqlite_test",2);
            SQLiteDatabase database=databaseHelpse.getReadableDatabase();
            Cursor cursor=database.rawQuery("select * from user",null);
            while (cursor.moveToNext())
            {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
//                int id_index=cursor.getColumnIndex("id");
                String name=cursor.getString(cursor.getColumnIndex("name"));
//                int name_index=cursor.getColumnIndex("name");
                System.out.println("query----->"+id+"---"+name);
//                System.out.println("id index is "+ id_index);
//                System.out.println("name index is "+name_index);
            }
        }
    }
}
