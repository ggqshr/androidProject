package com.example.ggq.httptest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ggq.httptest.HttpUtil.HttpUtilConn;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj.toString().equals("OK"))
            {
                result = "server recive";

            }
            else if(msg.toString().equals("Error"))
            {
                result= "Error";
            }
            textView.setText(result);
        }
    };
    TextView textView = null;
    String result = "Error";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new oos());
        textView = (TextView) findViewById(R.id.textView);
    }

    class oos implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            ConnectionHttp connectionHttp = new ConnectionHttp();
            connectionHttp.start();
            System.out.println("@@@@@@"+result);
            }


        }
        class ConnectionHttp extends Thread
        {
            @Override
            public void run() {
                Message message = new Message();
                System.out.println("!!!!!!");
                HttpUtilConn httpUtilConn = new HttpUtilConn();
                String url = "http://192.168.191.1:8080/RequestTest/Test1";
                HashMap<String,String> map = new HashMap<>();
                map.put("name","ggq");
                map.put("id","1");
                message.obj= httpUtilConn.sendPost(url,map);
                handler.sendMessage(message);
                super.run();
            }
        }
    }


