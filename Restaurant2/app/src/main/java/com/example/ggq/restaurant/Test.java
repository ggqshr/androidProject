package com.example.ggq.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ggq.restaurant.Util.HttpConnectionHelper;
import com.example.ggq.restaurant.com.Hotpot.entity.Food;
public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    public void Teston(View v)
    {
        new send().start();
    }
    class send extends Thread
    {
        @Override
        public void run() {
            super.run();
            String url  = "http://192.168.191.1:8080/Hotpotserver/Test";
            Food food = new Food();
            food.setFoodName("你好");
            HttpConnectionHelper httpConnectionHelper = new HttpConnectionHelper();
            String result = httpConnectionHelper.sendFood(url,food);
        }
    }
}
