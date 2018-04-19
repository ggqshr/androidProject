package com.example.ggq.calcultor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    int result1;
    EditText num1;
    EditText num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mybutton=(Button)findViewById(R.id.button);
        num1=(EditText)findViewById(R.id.editText5);
        num2=(EditText)findViewById(R.id.editText6);
        Log.d("@@@@@@@@@@",Integer.toString(result1));
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num11=Integer.parseInt(num1.getText().toString());
                int num22=Integer.parseInt(num2.getText().toString());
                result1=num11*num22;
                Intent intent=new Intent();
                intent.putExtra("result",Integer.toString(result1));
                intent.setClass(MainActivity.this,result.class);
                startActivity(intent);
            }
        });
    }
}
