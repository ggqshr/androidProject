package com.example.ggq.bindtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ggq.bindtest.util.user;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BlankFragment fragment ;
    item fragment1;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("tag","@@@@@");

            transaction.detach(fragment1).commit();
            Toast.makeText(MainActivity.this,"11111",Toast.LENGTH_SHORT).show();
        }
    };
    private ArrayList<user> muser ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            SharedPreferences preference =  getSharedPreferences("count",MODE_PRIVATE);
            int count = preference.getInt("count",0);

//        if(count==0)
//        {
//            Intent intent = new Intent();
//            intent.setClass(getApplicationContext(),Welcome.class);
//            startActivity(intent);
//            this.finish();
//            return ;
//        }
//        fragment = new BlankFragment();
//            fragment1 = new item();
//        fragmentManager = getSupportFragmentManager();
//        transaction =  fragmentManager.beginTransaction();
//        transaction.replace(R.id.main1,fragment1).commit();
//        transaction = fragmentManager.beginTransaction();
//        new Thread()
//        {
//            @Override
//            public void run() {
//                try {
//                    sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Message message = new Message();
//                handler.sendEmptyMessage(1);
//                super.run();
//            }
//        }.start();
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt("count",++count);
        editor.commit();
            RecyclerView rv = (RecyclerView) findViewById(R.id.recycle);
            muser = initdata();
            rv.setAdapter(new s.myad(this,muser));
            rv.setLayoutManager(new LinearLayoutManager(this));
            startActivity(new Intent().setClass(MainActivity.this,MainWelcome.class));
    }
    public ArrayList<user> initdata()
    {
        ArrayList<user> muser = new ArrayList<>();
    user user = new user();
        user.setDsc("@@@@@");
        user.setName("222");
        user.setImage(R.mipmap.ic_launcher_round);

        muser.add(user);
    user = new user();
        user.setDsc("2");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("3");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("4");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("5");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("6");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("7");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("8");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("9");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);

        user = new user();
        user.setDsc("10");
        user.setName("%%%%");
        user.setImage(R.mipmap.ic_launcher_round);
        muser.add(user);
        return muser;
}
}
