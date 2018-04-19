package com.example.ggq.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    ProgressBar bar=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=(ProgressBar)findViewById(R.id.progressBar3);
        Button button=(Button)findViewById(R.id.button5);
        button.setOnClickListener(new sson());
    }
    class sson implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            bar.setVisibility(View.VISIBLE);
            handler.post(rs);
        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            bar.setProgress(msg.arg1);
            handler.post(rs);
        }
    };
    Runnable rs=new Runnable() {
        int i=0;
        @Override
        public void run() {
            i=i+10;
            Message msg=handler.obtainMessage();
            msg.arg1=i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendMessage(msg);
            if(i==100)
            {
                handler.removeCallbacks(rs);
            }
        }
    };

}
