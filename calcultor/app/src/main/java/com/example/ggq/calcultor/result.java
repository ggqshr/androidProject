package com.example.ggq.calcultor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button button=(Button)findViewById(R.id.button2);
        button.setText(R.string.value);
        TextView textView=(TextView)findViewById(R.id.textView1);
        final Intent intent=getIntent();
        String ans=intent.getStringExtra("result");
        textView.setText(ans);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent();
                i1.setClass(result.this,MainActivity.class);
                startActivity(i1);
            }
        });
    }
}
