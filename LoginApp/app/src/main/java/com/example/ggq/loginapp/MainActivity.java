package com.example.ggq.loginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ggq.loginapp.util.DbUtil;
import com.example.ggq.loginapp.util.User;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    EditText e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        Button sign = (Button) findViewById(R.id.button2);
        login.setOnClickListener(new LoginListener());
        sign.setOnClickListener(new Signoc());
    }
    class LoginListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            System.out.println("%%%%%%%%%");
            String name = e1.getText().toString();
            String password = e2.getText().toString();
            DbUtil dbUtil =new DbUtil();

            User user = dbUtil.Query(MainActivity.this,name);
            if(user!=null)
            {

                if(user.getPassword().equals(password))
                {
                    intent.putExtra("name",name);
                    intent.setClass(MainActivity.this,LoginResult.class);
                    startActivity(intent);
                }
                else
                {
                    new AlertDialog.Builder(MainActivity.this).setTitle("登录失败")
                            .setMessage("账号或者密码错误！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();

                }
            }
        }
    }
    class Signoc implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Sign.class);
            startActivity(intent);
        }
    }
}
