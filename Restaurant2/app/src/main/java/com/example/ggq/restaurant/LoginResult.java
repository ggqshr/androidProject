package com.example.ggq.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);
        Intent intent = getIntent();
        String name = (String)intent.getStringExtra("n");
        System.out.print(name+"*********");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("welcome to login "+name);
    }
}
