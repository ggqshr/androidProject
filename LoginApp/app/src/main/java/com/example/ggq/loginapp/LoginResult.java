package com.example.ggq.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);
        final Intent intent = getIntent();
        String name = (String)intent.getStringExtra("name");
        TextView textView =(TextView) findViewById(R.id.textView);
        textView.setText("welcome to login"+name);
        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(LoginResult.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
