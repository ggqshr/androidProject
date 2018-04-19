package com.example.ggq.testturn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FristActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        TextView mytext=(TextView)findViewById(R.id.textView);
        mytext.setText("18岁少女。");
        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("abc","被老师轮流布置作业");
                intent.setClass(FristActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        Button mybutton =(Button)findViewById(R.id.button);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("abc","巩光乾你真僵！");
                intent.setClass(FristActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
