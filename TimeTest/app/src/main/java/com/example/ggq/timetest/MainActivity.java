package com.example.ggq.timetest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView textView= null ;
    Timer timer =new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView6);
        Button button = ( Button )findViewById(R.id.button4);
        button.setOnClickListener(new oos());
}
    class  oos implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            timer.schedule(timerTask,1000,1000);
        }
    }
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
        super.handleMessage(msg);
        long current = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(current);
        textView.setText(time);
    }
    };
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
             handler.sendEmptyMessage(0);
        }
    };
}
