package com.example.ggq.loginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ggq.loginapp.util.DbUtil;
import com.example.ggq.loginapp.util.User;

public class Sign extends AppCompatActivity {

    EditText name = null;
    EditText pwd = null;
    Button button = null;
    TextView tip = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        tip = (TextView) findViewById(R.id.textView4);
        name = (EditText) findViewById(R.id.editText3);
        name.setOnClickListener(new tipoc());
        pwd = (EditText)findViewById(R.id.editText4);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new signoc());

    }
    class tipoc implements TextView.OnClickListener
    {
        @Override
        public void onClick(View view) {
            tip.setText("3-16个字符");
        }
    }
    class signoc implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            String number = name.getText().toString();
            String password = pwd.getText().toString();
            if(number .equals("") || password.equals(""))
            {
                new AlertDialog.Builder(Sign.this).setTitle("提示").setMessage("账号和密码不能为空！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
            else
            {
                DbUtil util = new DbUtil();
                User user = new User(number,password);
                util.Insert(Sign.this,user);
                new AlertDialog.Builder(Sign.this).setTitle("提示")
                        .setMessage("注册成功返回登录")
                        .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setClass(Sign.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        }
    }
}
