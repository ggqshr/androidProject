package com.example.ggq.testturn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
        String v=intent.getStringExtra("abc");
        Log.d("@@@@@@@",v);
        TextView mytextview=(TextView)findViewById(R.id.textView2);
        mytextview.setText(v);
        Button mybutton =(Button)findViewById(R.id.button2);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(SecondActivity.this,FristActivity.class);
                startActivity(intent);
            }
        });
    }
}
