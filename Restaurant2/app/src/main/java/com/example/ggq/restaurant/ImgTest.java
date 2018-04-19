package com.example.ggq.restaurant;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.ggq.restaurant.Util.HttpConnectionHelper;
import com.example.ggq.restaurant.Util.HttpError;

public class ImgTest extends AppCompatActivity {

    final  static  int BitmapErrot = 1;
    final  static int NetErrot = 2;

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1!=BitmapErrot&&msg.arg1!=NetErrot)
            {
                ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                imageView.setImageBitmap((Bitmap) msg.obj);
                super.handleMessage(msg);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_test);
    }
    public void on1(View v)
    {
        //开启一个线程
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //1: 确定网址
                String path = "http://192.168.191.1:8080/Hotpotserver/img/118474-106.jpg";
                HttpConnectionHelper h = new HttpConnectionHelper();
                Bitmap bm =h.getImgFromUrl(path,new httpError());
                        Message msg = handler.obtainMessage();
                        msg.obj = bm;
                        handler.sendMessage(msg);
            }
        };

        //启动线程任务
        thread.start();
    }
    public class httpError implements HttpError
    {
        Message message = new Message();
        @Override
        public void onFinsh(String einf) {

        }

        @Override
        public void onError(String einf) {
            message.arg1 = BitmapErrot;
            handler.sendMessage(message);
        }

        @Override
        public void netError(String einf) {
            message.arg1 = NetErrot;
            handler.sendMessage(message);
        }
    }

}
