package com.example.ggq.restaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ggq.restaurant.Util.HttpConnectionHelper;
import com.example.ggq.restaurant.Util.HttpError;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView username = null;
    TextView userpwd = null;
    Button login = null;
    Button sign = null;
    EditText name = null;
    EditText pwd = null;
    boolean result = false;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MainActivity.this,msg.obj.toString(), Toast.LENGTH_LONG).show();
            if(msg.arg1==1&&msg.obj.toString().equals("login success"))
            {
                result = true;
                LoginSx();
            }
            super.handleMessage(msg);
        }
    };
    private View viewById;

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ImgTest.class);
                startActivity(intent);
        }
    }
    class httpErrot implements HttpError
    {
        Message message = new Message();
        @Override
        public void onFinsh(String einf) {
            message.obj = einf;
            handler.sendMessage(message);
        }

        @Override
        public void onError(String einf) {
            message.obj = einf;
            handler.sendMessage(message);
        }

        @Override
        public void netError(String einf) {
            message.obj = einf;
            handler.sendMessage(message);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init(){
        viewById = findViewById(R.id.textView3);
        username = (TextView) viewById;
        userpwd = (TextView) findViewById(R.id.textView4);
        login = (Button) findViewById(R.id.button2);
        sign = (Button) findViewById(R.id.button3);
        name = (EditText) findViewById(R.id.editText);
        pwd = (EditText) findViewById(R.id.editText1);
        login.setOnClickListener(new loginos());
//        sign.setOnClickListener(new signos());
    }

    public void LoginSx()
    {
        System.out.println("&&&&&&&&&&&&&&&");
        if(result)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,LoginResult.class);
            String name1 = name.getText().toString();
            intent.putExtra("n",name1);
            startActivity(intent);
        }
        else
        {
                new AlertDialog.Builder(MainActivity.this).setTitle("登陆失败").setMessage("账号或者密码错误")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
        }
    }
    class loginos implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            loginThread l = new loginThread();
            l.start();

        }
    }
    class loginThread extends Thread
    {
        @Override
        public void run() {
            Message message = new Message();
            message.obj = "";
            String r = "";
            String url = "http://192.168.191.1:8080/RequestTest/T1";
            HashMap<String,String> map = new HashMap<>();
            map.put("name",name.getText().toString());
            map.put("pwd",pwd.getText().toString());
            HttpConnectionHelper con = new HttpConnectionHelper();
            httpErrot errot = new httpErrot();
            r = con.sendPost(url,map,errot);
            message.obj = r;
            System.out.println(r+"%%%%%");
            if(!r.equals("error"))
            {
                message.arg1 = 1;
            }
            handler.sendMessage(message);
            super.run();
        }
    }
    public void on2(View view)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,Test.class);
        startActivity(intent);
    }
}
