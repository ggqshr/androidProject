package com.example.ggq.bindtest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainWelcome extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainwelcome);
        item f = new item();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.change,f).commit();
        ImageButton imageButton = (ImageButton) findViewById(R.id.imagebutton);
        ImageButton i1 = (ImageButton)findViewById(R.id.user);
        i1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("@@@@@@@@@@@@@@" ,"DOWN ");
                    v.setBackgroundResource(R.mipmap.pressed_user);

                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("@@@@@@@@@@@@@@" ,"UP ");
                    v.setBackgroundResource(R.drawable.user);
                }
                return false;
            }
        });
        ImageButton i2 = (ImageButton)findViewById(R.id.main);
        i2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("@@@@@@@@@@@@@@" ,"DOWN ");
                    v.setBackgroundResource(R.mipmap.press_main);

                }else if(event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("@@@@@@@@@@@@@@" ,"UP ");
                    v.setBackgroundResource(R.drawable.main);
                }
                return false;
            }
        });
    }
//    public void onLine(View e)
//    {
//        switch (e.getId()){
//            case R.id.main:
//                textView.setText("主界面");
//                break;
//            case R.id.business:
//                textView.setText("订单");
//                break;
//            case R.id.user:
//                textView.setText("用户");
//                break;
//            case R.id.other:
//                textView.setText("其他");
//                break;
//        }
//    }
}
